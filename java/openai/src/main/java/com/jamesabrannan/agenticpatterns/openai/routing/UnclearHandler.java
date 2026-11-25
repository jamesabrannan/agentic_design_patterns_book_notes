package com.jamesabrannan.agenticpatterns.openai.routing;

import com.jamesabrannan.agenticpatterns.openai.provider.OpenAiTextService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Acknowledges unclear requests using the shared OpenAI text service.
 *
 * @file UnclearHandler.java
 * @author James Brannan
 * @date 2026-11-25
 * @version 0.0.1-SNAPSHOT
 * @since 0.0.1-SNAPSHOT
 * @copyright Copyright (c) 2026 James Brannan.
 * @license MIT License. See LICENSE in the project root for the full terms.
 *          SPDX-License-Identifier: MIT
 */
public class UnclearHandler implements RequestHandler {

    private static final Logger logger = LoggerFactory.getLogger(UnclearHandler.class);

    private static final String INSTRUCTIONS = """
            Reply only with: Your request was unclear.
            Do not ask questions or request additional details.
            """;

    private final OpenAiTextService textService;

    public UnclearHandler(OpenAiTextService textService) {
        this.textService = textService;
    }

    @Override
    public String handle(String request) {
        logger.info("Handling unclear request");
        return textService.generate(INSTRUCTIONS, request);
    }
}
