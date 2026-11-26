package com.jamesabrannan.springai.agenticpatterns.routing.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

@Component
public class BookingHandler extends RequestHandler {

    private static final Logger logger = LoggerFactory.getLogger(BookingHandler.class);

    private static final String INSTRUCTIONS = """
            You are a booking assistant.
            Acknowledge the user's travel request without asking questions or requesting additional details.
            Keep your response brief.
            Do not claim to have booked anything or checked live availability.
            """;

    public BookingHandler(ChatClient chatClient) {
        super(chatClient);
        logger.debug("BookingHandler ready with category-specific instructions");
    }

    @Override
    public String handle(String request) {
        logger.debug("Generating booking response");
        logger.debug("Sending handler instructions and user request to the model");
        String response = chatClient.prompt()
                .system(INSTRUCTIONS)
                .user(request)
                .call()
                .content();
        logger.debug("Booking model response received; returning to coordinator");
        return response;
    }

}
