package com.example.tickettriage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.example.tickettriage.model.ChatCompletionRequest;
import com.example.tickettriage.model.ChatCompletionResponse;
import com.example.tickettriage.model.ChatMessage;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HuggingFaceService {

    private final RestClient restClient;
    private final String model;

    public HuggingFaceService(
            @Value("${huggingface.api.token}") String token,
            @Value("${huggingface.api.model}") String model) {
        this.model = model;
        this.restClient = RestClient.builder()
                .baseUrl("https://router.huggingface.co/v1")
                .defaultHeader("Authorization", "Bearer " + token)
                .build();
    }

    public String ask(String systemPrompt, String userPrompt) {
        var request = new ChatCompletionRequest(model, List.of(
                new ChatMessage("system", systemPrompt),
                new ChatMessage("user", userPrompt)
        ));

        ChatCompletionResponse response = restClient.post()
                .uri("/chat/completions")
                .body(request)
                .retrieve()
                .body(ChatCompletionResponse.class);

        return response.choices().get(0).message().content();
    }
}