package com.jamesabrannan.springai.agenticpatterns.routing.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

@Component
public class InfoHandler extends RequestHandler {

    private static final String INSTRUCTIONS = """
            You are an information assistant.
            Answer the user's general knowledge question briefly and clearly.
            Acknowledge uncertainty when you do not know the answer.
            Do not claim to have searched the web or checked live information.
            Do not ask questions or request additional details.
            """;

    private static final Logger logger = LoggerFactory.getLogger(InfoHandler.class);

    public InfoHandler(ChatClient chatClient) {
        super(chatClient);
        logger.debug("InfoHandler ready with category-specific instructions");
    }

    @Override
    public String handle(String request) {
        logger.debug("Generating information response");
        logger.debug("Sending handler instructions and user request to the model");
        String response = chatClient.prompt()
                .system(INSTRUCTIONS)
                .user(request)
                .call()
                .content();
        logger.debug("Information model response received; returning to coordinator");
        return response;
    }

}
