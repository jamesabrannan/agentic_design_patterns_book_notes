package com.jamesabrannan.agenticpatterns.openai.routing;

import com.jamesabrannan.agenticpatterns.openai.provider.OpenAiTextService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Classifies requests into routing categories using the shared OpenAI text service.
 *
 * @file CoordinatorAgent.java
 * @author James Brannan
 * @date 2026-11-25
 * @version 0.0.1-SNAPSHOT
 * @since 0.0.1-SNAPSHOT
 * @copyright Copyright (c) 2026 James Brannan.
 * @license MIT License. See LICENSE in the project root for the full terms.
 *          SPDX-License-Identifier: MIT
 */
public class CoordinatorAgent {

    private static final Logger logger = LoggerFactory.getLogger(CoordinatorAgent.class);

    private static final String INSTRUCTIONS = """
            Classify the user's request into exactly one category:
            BOOKING: Requests related to booking flights or hotels.
            INFO: General information questions.
            UNCLEAR: Ambiguous requests or requests outside those categories.
            Return only BOOKING, INFO, or UNCLEAR. Do not answer the request itself.
            """;

    private final OpenAiTextService textService;

    public CoordinatorAgent(OpenAiTextService textService) {
        this.textService = textService;
    }

    /**
     * Calls OpenAI to classify the request and converts its answer to a category.
     *
     * @param request the original user request
     * @return the category used to select a handler
     * @throws IllegalArgumentException if the model returns an unsupported category
     */
    public RequestCategory classify(String request) {
        String response = textService.generate(INSTRUCTIONS, request);
        RequestCategory category = RequestCategory.valueOf(response.trim());
        logger.info("Request category: {}", category);
        return category;
    }
}
