package com.jamesabrannan.agenticpatterns.openai.routing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Skeleton for handling booking requests in the OpenAI routing example.
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

    @Override
    public String handle(String request) {
        logger.debug("handle called: requestCharacters={}", request == null ? null : request.length());
        logger.error("handle is not implemented");
        throw new UnsupportedOperationException("BookingHandler.handle is not implemented");
    }
}
