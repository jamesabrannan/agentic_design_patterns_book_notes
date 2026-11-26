package com.jamesabrannan.springai.agenticpatterns.routing.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

@Component
public class UnclearHandler extends RequestHandler {

    public UnclearHandler(ChatClient chatClient) {
        super(chatClient);
        logger.debug("UnclearHandler ready with category-specific instructions");
    }

    private static final String INSTRUCTIONS = """
            Reply only with: Your request was unclear.
            Do not ask questions or request additional details.
            """;

    private static final Logger logger = LoggerFactory.getLogger(UnclearHandler.class);

    @Override
    public String handle(String request) {
        logger.debug("Generating unclear-request response");
        logger.debug("Sending handler instructions and user request to the model");
        String response = chatClient.prompt()
                .system(INSTRUCTIONS)
                .user(request)
                .call()
                .content();
        logger.debug("Unclear-request model response received; returning to coordinator");
        return response;
    }

}
