package com.jamesabrannan.agenticpatterns.openai.routing;
import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;

public class RoutingExample {

	public static void main(String[] args) {
		
		OpenAIClient client = OpenAIOkHttpClient.fromEnv();

	}

}
