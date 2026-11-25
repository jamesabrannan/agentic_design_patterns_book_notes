package com.jamesabrannan.agenticpatterns.openai.routing;

import com.jamesabrannan.agenticpatterns.openai.provider.OpenAiTextService;
import com.jamesabrannan.agenticpatterns.openai.provider.ProviderModelSettings;
import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Demonstrates routing with the OpenAI Responses API and ordinary Java control flow.
 *
 * <p>The coordinator asks OpenAI to classify each sample request. Java selects one
 * handler, which sends the original request to OpenAI with its own instructions.
 * Each successful sample therefore normally makes two model calls, excluding retries.
 * The booking handler acknowledges requests; it does not perform real bookings.
 */
public class RoutingExample {

    private static final Logger logger = LoggerFactory.getLogger(RoutingExample.class);

    /**
     * Constructs the shared services and processes three sample requests sequentially.
     * Requires OPENAI_API_KEY in the process environment and makes live API calls.
     * Failures propagate to the caller after the client is closed.
     *
     * @param args unused command-line arguments
     */
    public static void main(String[] args) {
        // Read SDK configuration from the environment; construction sends no model request.
        OpenAIClient client = OpenAIOkHttpClient.fromEnv();
        try {
            // Reuse one client and model configuration across the coordinator and handlers.
            OpenAiTextService textService =
                    new OpenAiTextService(client, ProviderModelSettings.MODEL_NAME);
            CoordinatorAgent coordinatorAgent = new CoordinatorAgent(textService);
            BookingHandler bookingHandler = new BookingHandler(textService);
            InfoHandler infoHandler = new InfoHandler(textService);
            UnclearHandler unclearHandler = new UnclearHandler(textService);

            // Intended to exercise all three categories; actual classification is model-driven.
            String[] requests = {
                "Book me a flight to London.",
                "What is the capital of Italy?",
                "Do that thing for me."
            };

            for (String request : requests) {
                logger.info("Request: {}", request);
                // First model call: obtain a category, without answering the request.
                RequestCategory category = coordinatorAgent.classify(request);
                
                logger.debug("returned: " + category.name());
                
                // Java selects the handler; the switch itself makes no API call.
                RequestHandler handler = switch (category) {
                    case BOOKING -> bookingHandler;
                    case INFO -> infoHandler;
                    case UNCLEAR -> unclearHandler;
                };
                // Second model call: the selected handler receives the original request.
                String response = handler.handle(request);
                // Escape line breaks so model output stays within one log entry.
                logger.info("Response: {}", response.replace("\r", "\\r").replace("\n", "\\n"));
            }
        } finally {
            client.close();
        }
    }
}
