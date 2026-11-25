package com.jamesabrannan.agenticpatterns.openai.routing;

import com.jamesabrannan.agenticpatterns.openai.provider.OpenAiTextService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Answers general information requests using the shared OpenAI text service.
 *
 * @file InfoHandler.java
 * @author James Brannan
 * @date 2026-11-25
 * @version 0.0.1-SNAPSHOT
 * @since 0.0.1-SNAPSHOT
 * @copyright Copyright (c) 2026 James Brannan.
 * @license MIT License. See LICENSE in the project root for the full terms.
 *          SPDX-License-Identifier: MIT
 */
public class InfoHandler implements RequestHandler {

    private static final Logger logger = LoggerFactory.getLogger(InfoHandler.class);

    private static final String INSTRUCTIONS = """
            You are an information assistant.
            Answer the user's general knowledge question briefly and clearly.
            Acknowledge uncertainty when you do not know the answer.
            Do not claim to have searched the web or checked live information.
            Do not ask questions or request additional details.
            """;

    private final OpenAiTextService textService;

    public InfoHandler(OpenAiTextService textService) {
        this.textService = textService;
    }

    @Override
    public String handle(String request) {
        logger.info("Handling information request");
        return textService.generate(INSTRUCTIONS, request);
    }
}
