package com.jamesabrannan.agenticpatterns.langchain4j.routing;

import dev.langchain4j.agentic.Agent;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

/**
 * Declares a model-backed handler for booking inquiries.
 *
 * <p>Example messages after substituting the original request (not a captured API log):
 * <pre>{@code
 * SYSTEM:
 * You are a booking assistant.
 * Acknowledge the user's travel request without asking questions or requesting additional details.
 * Keep your response brief.
 * Do not claim to have booked anything or checked live availability.
 * Do not ask questions or request additional details.
 * 
 * USER:
 * Book me a flight to London.
 * }</pre>
 *
 * @file BookingHandler.java
 * @author James Brannan
 * @date 2026-11-24
 * @version 0.0.1-SNAPSHOT
 * @since 0.0.1-SNAPSHOT
 * @copyright Copyright (c) 2026 James Brannan.
 * @license MIT License. See LICENSE in the project root for the full terms.
 *          SPDX-License-Identifier: MIT
 */
public interface BookingHandler {

    @Agent("Handles flight and hotel booking inquiries")
    @SystemMessage("""
            You are a booking assistant.
            Acknowledge the user's travel request without asking questions or requesting additional details.
            Keep your response brief.
            Do not claim to have booked anything or checked live availability.
            Do not ask questions or request additional details.
            """)
    // Bind the method argument to the user-message template.
    @UserMessage("{{request}}")
    String handle(@V("request") String request);
}
