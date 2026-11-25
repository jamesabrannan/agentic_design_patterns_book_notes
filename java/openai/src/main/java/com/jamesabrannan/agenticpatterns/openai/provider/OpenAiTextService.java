package com.jamesabrannan.agenticpatterns.openai.provider;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.openai.client.OpenAIClient;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;
import com.openai.models.responses.ResponseStatus;
import java.util.Objects;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.openai.core.ObjectMappers.jsonMapper;

/**
 * Sends text requests through the OpenAI Responses API and extracts generated text.
 *
 * <p>This demo service logs full instructions, input, and response JSON at DEBUG.
 * Use only non-sensitive sample data. The caller owns the shared client's lifecycle.
 *
 * @file OpenAiTextService.java
 * @author James Brannan
 * @date 2026-11-25
 * @version 0.0.1-SNAPSHOT
 * @since 0.0.1-SNAPSHOT
 * @copyright Copyright (c) 2026 James Brannan.
 * @license MIT License. See LICENSE in the project root for the full terms.
 *          SPDX-License-Identifier: MIT
 */
public class OpenAiTextService {

    private static final Logger logger = LoggerFactory.getLogger(OpenAiTextService.class);

    private final OpenAIClient client;
    private final String modelName;

    public OpenAiTextService(OpenAIClient client, String modelName) {
        logger.trace("Constructing OpenAiTextService; clientPresent={}, modelNamePresent={}",
                client != null, modelName != null);
        this.client = Objects.requireNonNull(client, "client must not be null");
        this.modelName = Objects.requireNonNull(modelName, "modelName must not be null");
        if (modelName.isBlank()) {
            throw new IllegalArgumentException("modelName must not be blank");
        }
        logger.trace("OpenAiTextService construction complete");
    }

    /**
     * Generates text from instructions and a non-sensitive sample request.
     *
     * @param instructions behavioral instructions for the model
     * @param request original user request
     * @return generated response text
     * @throws IllegalStateException if the response is incomplete, refused, or empty
     */
    public String generate(String instructions, String request) {
        logger.debug("generate inputs: instructionsPresent={}, requestPresent={}",
                instructions != null, request != null);
        try {
            Objects.requireNonNull(instructions, "instructions must not be null");
            Objects.requireNonNull(request, "request must not be null");

            // Building parameters is local; create() performs the API call.
            ResponseCreateParams params = ResponseCreateParams.builder()
                    .model(modelName)
                    .instructions(instructions)
                    .input(request)
                    .build();

            logger.debug("OpenAI prompt: instructions={}, input={}",
                    instructions.replace("\r", "\\r").replace("\n", "\\n"),
                    request.replace("\r", "\\r").replace("\n", "\\n"));
            Response response = client.responses().create(params);

            // Serialize the returned SDK object before extracting application text.
            if (logger.isDebugEnabled()) {
                try {
                    logger.debug("OpenAI response JSON: {}", jsonMapper().writeValueAsString(response));
                } catch (JsonProcessingException exception) {
                    // Logging failure must not discard an otherwise valid response.
                    logger.warn("Response JSON logging failed: {}", exception.getClass().getSimpleName());
                }
            }

            if (!response.status().filter(ResponseStatus.COMPLETED::equals).isPresent()) {
                throw new IllegalStateException("OpenAI response did not complete successfully");
            }

            boolean refused = response.output().stream()
                    .flatMap(item -> item.message().stream())
                    .flatMap(message -> message.content().stream())
                    .anyMatch(content -> content.refusal().isPresent());
            if (refused) {
                throw new IllegalStateException("OpenAI refused the request");
            }

            // Responses can contain output items other than message text.
            String text = response.output().stream()
                    .flatMap(item -> item.message().stream())
                    .flatMap(message -> message.content().stream())
                    .flatMap(content -> content.outputText().stream())
                    .map(outputText -> outputText.text())
                    .collect(Collectors.joining());

            if (text.isBlank()) {
                throw new IllegalStateException("OpenAI returned no generated text");
            }
            logger.debug("generate completed: responseCharacters={}", text.length());
            return text;
        } catch (RuntimeException exception) {
            // Avoid exception messages that might contain request data or credentials.
            logger.error("generate failed: {}", exception.getClass().getSimpleName());
            throw exception;
        }
    }
}
