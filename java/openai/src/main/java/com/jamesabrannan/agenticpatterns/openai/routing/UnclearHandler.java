package com.jamesabrannan.agenticpatterns.openai.routing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Skeleton for handling unclear requests in the OpenAI routing example.
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

    @Override
    public String handle(String request) {
        logger.debug("handle called: requestCharacters={}", request == null ? null : request.length());
        logger.error("handle is not implemented");
        throw new UnsupportedOperationException("UnclearHandler.handle is not implemented");
    }
}
