package com.jamesabrannan.agenticpatterns.openai.routing;

/**
 * Defines the shared handler contract for the OpenAI routing example.
 *
 * @file RequestHandler.java
 * @author James Brannan
 * @date 2026-11-25
 * @version 0.0.1-SNAPSHOT
 * @since 0.0.1-SNAPSHOT
 * @copyright Copyright (c) 2026 James Brannan.
 * @license MIT License. See LICENSE in the project root for the full terms.
 *          SPDX-License-Identifier: MIT
 */
public interface RequestHandler {

    /**
     * Handles the original request and returns a response.
     *
     * @param request the original user request
     * @return the handler's response
     */
    String handle(String request);
}
