package com.jamesabrannan.agenticpatterns.langchain4j.routing;

import java.util.Map;

import com.jamesabrannan.agenticpatterns.langchain4j.provider.ProviderModelSettings;

import dev.langchain4j.agentic.AgenticServices;
import dev.langchain4j.agentic.UntypedAgent;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Demonstrates request classification followed by conditional routing to model-backed handlers.
 *
 * <p>The routing pattern classifies an incoming request and directs it to a specialized handler.
 * It separates responsibilities so each handler can use instructions tailored to its category.
 * See <a href="https://www.anthropic.com/research/building-effective-agents">Anthropic's
 * Building Effective Agents</a>, under "Workflow: Routing".
 *
 * <p>Constructs the agents once, then classifies each sample request and passes its
 * category and original text to the conditional workflow. The selected handler
 * receives its system instructions and the original request, as illustrated in
 * the handler interfaces' Javadoc. The category selects the branch and is not
 * included in the handler's user message.
 *
 * @file RoutingExample.java
 * @author James Brannan
 * @date 2026-11-24
 * @version 0.0.1-SNAPSHOT
 * @since 0.0.1-SNAPSHOT
 * @copyright Copyright (c) 2026 James Brannan.
 * @license MIT License. See LICENSE in the project root for the full terms.
 *          SPDX-License-Identifier: MIT
 */
public class RoutingExample {

    private static final Logger logger =
            LoggerFactory.getLogger(RoutingExample.class);

    public static void main(String[] args) {

        // Share one configured model across the classifier and handlers.
        OpenAiChatModel model = OpenAiChatModel.builder()
                .apiKey(ProviderModelSettings.API_KEY)
                .modelName(ProviderModelSettings.MODEL_NAME)
                .build();

        // LangChain4j supplies implementations of the annotated interfaces.
        CoordinatorAgent coordinatorAgent = AgenticServices.agentBuilder(CoordinatorAgent.class)
                .chatModel(model)
                .outputKey("response")
                .build();

        // Each selected handler stores its answer under the same output key.
        BookingHandler bookingHandler = AgenticServices.agentBuilder(BookingHandler.class)
                .chatModel(model)
                .outputKey("response")
                .build();

        InfoHandler infoHandler = AgenticServices.agentBuilder(InfoHandler.class)
                .chatModel(model)
                .outputKey("response")
                .build();

        UnclearHandler unclearHandler = AgenticServices.agentBuilder(UnclearHandler.class)
                .chatModel(model)
                .outputKey("response")
                .build();

        // Match the supplied category to a handler; return its stored response.
        UntypedAgent handlers = AgenticServices
                .conditionalBuilder()
                .subAgents(
                        scope -> scope.readState("category") == RequestCategory.BOOKING,
                        bookingHandler)
                .subAgents(
                        scope -> scope.readState("category") == RequestCategory.INFO,
                        infoHandler)
                .subAgents(
                        scope -> scope.readState("category") == RequestCategory.UNCLEAR,
                        unclearHandler)
                .output(scope -> scope.readState("response"))
                .build();

        // Sample inputs intended to exercise all three branches.
        String[] requests = {
            "Book me a flight to London.",
            "What is the capital of Italy?",
            "Do that thing for me."
        };

        for (String request : requests) {
            // First model call: classify the original request.
            RequestCategory category = coordinatorAgent.classify(request);

            // Supply shared state for branch conditions and handler arguments.
            Map<String, Object> arguments = Map.of(
                    "request", request,
                    "category", category
            );

            // Second model call: execute only the matching handler.
            Object response = handlers.invoke(arguments);

            logger.info("Request: {}", request);
            logger.info("Category: {}", category);
            logger.info("Response: {}", response);
        }
    }
}
