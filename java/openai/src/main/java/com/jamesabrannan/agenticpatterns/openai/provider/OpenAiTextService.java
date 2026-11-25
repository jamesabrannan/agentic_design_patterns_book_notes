package com.jamesabrannan.agenticpatterns.openai.provider;

import static com.openai.core.ObjectMappers.jsonMapper;

import com.openai.client.OpenAIClient;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;
import com.openai.models.responses.ResponseStatus;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Sends text requests through the OpenAI Responses API and extracts generated text.
 *
 * <p>The caller owns the shared client's lifecycle.
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
        this.client = client;
        this.modelName = modelName;
    }

    /**
     * Calls the OpenAI Responses API with instructions and a non-sensitive sample
     * request, then returns the generated text.
     *
     * @param instructions behavioral instructions for the model
     * @param request original user request
     * @return generated response text
     * @throws IllegalStateException if the response is incomplete, refused, or empty
     */
    public String generate(String instructions, String request) {
        // Building parameters is local; create() performs the API call.
        ResponseCreateParams params = ResponseCreateParams.builder()
                .model(modelName)
                .instructions(instructions)
                .input(request)
                .build();

        logger.debug("Calling OpenAI");
        Response response = client.responses().create(params);

        //logger.debug("OpenAI response JSON: {}", jsonMapper().valueToTree(response).toString());

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
        return text;
    }
}
