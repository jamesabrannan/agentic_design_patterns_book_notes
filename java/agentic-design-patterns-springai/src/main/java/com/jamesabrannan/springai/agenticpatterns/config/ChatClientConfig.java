package com.jamesabrannan.springai.agenticpatterns.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Provides a shared ChatClient using Spring AI's auto-configured OpenAI model.
 *
 * <p>OpenAI connection settings are supplied through application.properties,
 * including the API key reference to the OPENAI_API_KEY environment variable.
 *
 * @file ChatClientConfig.java
 * @author James Brannan
 * @date 2026-11-26
 * @version 0.0.1-SNAPSHOT
 * @since 0.0.1-SNAPSHOT
 * @copyright Copyright (c) 2026 James Brannan.
 * @license MIT License. See LICENSE in the project root for the full terms.
 *          SPDX-License-Identifier: MIT
 */
@Configuration
public class ChatClientConfig {

    private static final Logger logger = LoggerFactory.getLogger(ChatClientConfig.class);

    /**
     * Builds one shared client for constructor injection into the routing handlers.
     *
     * @param builder the auto-configured chat client builder
     * @return the shared chat client
     */
    @Bean
    public ChatClient routingChatClient(ChatClient.Builder builder) {
        logger.debug("Creating shared routing ChatClient");
        ChatClient chatClient = builder.build();
        logger.debug("Shared routing ChatClient ready for injection");
        return chatClient;
    }
}
