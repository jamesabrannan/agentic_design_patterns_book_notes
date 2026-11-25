package com.jamesabrannan.agenticpatterns.langchain4j.provider;

/**
 * Provides the OpenAI API key from the environment and the model name for examples.
 *
 * @file ProviderModelSettings.java
 * @author James Brannan
 * @date 2026-11-24
 * @version 0.0.1-SNAPSHOT
 * @since 0.0.1-SNAPSHOT
 * @copyright Copyright (c) 2026 James Brannan.
 * @license MIT License. See LICENSE in the project root for the full terms.
 *          SPDX-License-Identifier: MIT
 */
public final class ProviderModelSettings {

	// Read the process environment; this does not load a .env file.
	final public static String API_KEY = System.getenv("OPENAI_API_KEY");
	final public static String MODEL_NAME = "gpt-4o-mini";

}
