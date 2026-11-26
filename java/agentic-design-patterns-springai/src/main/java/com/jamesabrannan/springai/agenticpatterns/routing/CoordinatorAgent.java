package com.jamesabrannan.springai.agenticpatterns.routing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

import com.jamesabrannan.springai.agenticpatterns.routing.handlers.BookingHandler;
import com.jamesabrannan.springai.agenticpatterns.routing.handlers.InfoHandler;
import com.jamesabrannan.springai.agenticpatterns.routing.handlers.RequestCategory;
import com.jamesabrannan.springai.agenticpatterns.routing.handlers.UnclearHandler;

@Component
public class CoordinatorAgent {

    private static final Logger logger = LoggerFactory.getLogger(CoordinatorAgent.class);

    private static final String INSTRUCTIONS = """
            Classify the user's request into exactly one category:
            BOOKING: Requests related to booking flights or hotels.
            INFO: General information questions.
            UNCLEAR: Ambiguous requests or requests outside those categories.
            Return only BOOKING, INFO, or UNCLEAR. Do not answer the request itself.
            """;

    private final BookingHandler bookingHandler;
    private final InfoHandler infoHandler;
    private final UnclearHandler unclearHandler;
    private final ChatClient chatClient;

    CoordinatorAgent(BookingHandler bookingHandler, InfoHandler infoHandler, UnclearHandler unclearHandler,
            ChatClient chatClient) {

        this.bookingHandler = bookingHandler;
        this.infoHandler = infoHandler;
        this.unclearHandler = unclearHandler;
        this.chatClient = chatClient;
        logger.debug("Coordinator initialized with three handlers and the shared ChatClient");
    }

    public String handleRequest(String request) {
        logger.debug("Received request; starting classification and handler workflow");
        logger.debug("Classifying request");
        String requestType = chatClient.prompt()
                .system(INSTRUCTIONS)
                .user(request)
                .call()
                .content();
        logger.debug("Classification response received; converting to RequestCategory");

        if (requestType == null || requestType.isBlank()) {
            throw new IllegalStateException("Classification returned no category; expected BOOKING, INFO, or UNCLEAR");
        }

        RequestCategory category;
        try {
            category = RequestCategory.valueOf(requestType.trim());
        } catch (IllegalArgumentException exception) {
            throw new IllegalStateException(
                    "Classification returned an invalid category; expected BOOKING, INFO, or UNCLEAR", exception);
        }
        logger.debug("Classification converted successfully: {}", category);
        logger.debug("Passing original request to the selected handler");

        String response = routeRequest(category, request);
        logger.debug("{} handler completed; returning final response", category);
        return response;
    }

    private String routeRequest(RequestCategory requestCategory, String request) {
        logger.debug("Routing request to {} handler", requestCategory);
        return switch (requestCategory) {
            case INFO -> infoHandler.handle(request);
            case BOOKING -> bookingHandler.handle(request);
            case UNCLEAR -> unclearHandler.handle(request);
        };
    }
}
