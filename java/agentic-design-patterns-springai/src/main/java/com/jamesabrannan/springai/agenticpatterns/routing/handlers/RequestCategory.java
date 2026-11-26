package com.jamesabrannan.springai.agenticpatterns.routing.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum RequestCategory {
    BOOKING,
    INFO,
    UNCLEAR;

    private static final Logger logger = LoggerFactory.getLogger(RequestCategory.class);

    static {
        logger.debug("Available routing categories: {}", (Object) values());
    }
}
