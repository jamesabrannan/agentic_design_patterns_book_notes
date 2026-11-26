package com.jamesabrannan.springai.agenticpatterns.routing.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jamesabrannan.springai.agenticpatterns.routing.CoordinatorAgent;

@Service
public class RoutingExampleService {

    private static final Logger logger = LoggerFactory.getLogger(RoutingExampleService.class);

    private static final List<String> REQUESTS = List.of(
            "Book me a flight to London.",
            "What is the capital of Italy?",
            "Do that thing for me.");

    private final CoordinatorAgent coordinator;

    RoutingExampleService(CoordinatorAgent coordinator) {
        this.coordinator = coordinator;
        logger.debug("Routing example service initialized with CoordinatorAgent");
    }

    public void runExample() {
        logger.debug("Processing {} sample requests", REQUESTS.size());

        int requestNumber = 0;
        for (String request : REQUESTS) {
            logger.debug("Submitting sample request {} of {}", ++requestNumber, REQUESTS.size());
            String response = coordinator.handleRequest(request);
            logger.debug("Sample request {} completed; displaying response", requestNumber);

            logger.info("""

                    ============================================================
                    REQUEST: {}

                    RESPONSE:
                    {}
                    ============================================================
                    """, request, response);
        }
        logger.debug("Finished processing all {} sample requests", REQUESTS.size());
    }
}
