package com.jamesabrannan.agenticpatterns.openai.routing;

import com.jamesabrannan.agenticpatterns.openai.provider.OpenAiTextService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Acknowledges travel booking requests using the shared OpenAI text service.
 *
 * @file BookingHandler.java
 * @author James Brannan
 * @date 2026-11-25
 * @version 0.0.1-SNAPSHOT
 * @since 0.0.1-SNAPSHOT
 * @copyright Copyright (c) 2026 James Brannan.
 * @license MIT License. See LICENSE in the project root for the full terms.
 *          SPDX-License-Identifier: MIT
 */
public class BookingHandler implements RequestHandler {

    private static final Logger logger = LoggerFactory.getLogger(BookingHandler.class);

    private static final String INSTRUCTIONS = """
            You are a booking assistant.
            Acknowledge the user's travel request without asking questions or requesting additional details.
            Keep your response brief.
            Do not claim to have booked anything or checked live availability.
            """;

    private final OpenAiTextService textService;

    public BookingHandler(OpenAiTextService textService) {
        this.textService = textService;
    }

    @Override
    public String handle(String request) {
        logger.info("Handling booking request");
        return textService.generate(INSTRUCTIONS, request);
    }
}
