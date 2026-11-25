package com.jamesabrannan.agenticpatterns.langchain4j.routing;

import dev.langchain4j.agentic.Agent;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

/**
 * Classifies requests into categories used by the conditional routing workflow.
 *
 * @file CoordinatorAgent.java
 * @author James Brannan
 * @date 2026-11-24
 * @version 0.0.1-SNAPSHOT
 * @since 0.0.1-SNAPSHOT
 * @copyright Copyright (c) 2026 James Brannan.
 * @license MIT License. See LICENSE in the project root for the full terms.
 *          SPDX-License-Identifier: MIT
 */
public interface CoordinatorAgent {

    @Agent("Classifies a request for routing to the appropriate handler")
    @SystemMessage("""
            Classify the user's request into exactly one category:
            BOOKING: Requests related to booking flights or hotels.
            INFO: General information questions.
            UNCLEAR: Ambiguous requests or requests outside those categories.
            Return only BOOKING, INFO, or UNCLEAR. Do not answer the request itself.
            """)
    // Bind the method argument to the user-message template.
    @UserMessage("{{request}}")
    RequestCategory classify(@V("request") String request);
}
