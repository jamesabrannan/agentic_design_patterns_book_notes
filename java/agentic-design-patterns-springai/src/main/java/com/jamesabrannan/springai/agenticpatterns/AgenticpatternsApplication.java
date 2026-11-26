package com.jamesabrannan.springai.agenticpatterns;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jamesabrannan.springai.agenticpatterns.routing.service.RoutingExampleService;

@SpringBootApplication
public class AgenticpatternsApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(AgenticpatternsApplication.class);

    private final RoutingExampleService routingExampleService;

    public AgenticpatternsApplication(RoutingExampleService routingExampleService) {
        this.routingExampleService = routingExampleService;
        logger.debug("Application initialized with RoutingExampleService");
    }

    public static void main(String[] args) {
        SpringApplication.run(AgenticpatternsApplication.class, args);
        logger.debug("Spring application and command-line runner completed startup processing");
    }

    @Override
    public void run(String... args) {
        logger.debug("Selecting example from {} command-line arguments", args.length);
        if (args.length > 0 && "ROUTING".equalsIgnoreCase(args[0])) {
            logger.debug("Running routing example");
            routingExampleService.runExample();
            logger.debug("Routing example finished");
        } else {
            logger.info("No supported example selected; use ROUTING");
        }
    }
}
