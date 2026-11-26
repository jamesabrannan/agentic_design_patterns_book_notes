package com.jamesabrannan.springai.agenticpatterns.routing.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;

public abstract class RequestHandler {

    private static final Logger logger = LoggerFactory.getLogger(RequestHandler.class);

    protected final ChatClient chatClient;

    protected RequestHandler(ChatClient chatClient) {
        this.chatClient = chatClient;
        logger.debug("{} initialized with the shared ChatClient", getClass().getSimpleName());
    }

    public abstract String handle(String request);
}
