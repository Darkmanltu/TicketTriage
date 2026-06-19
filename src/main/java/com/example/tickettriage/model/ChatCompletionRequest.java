package com.example.tickettriage.model;
import java.util.List;

public record ChatCompletionRequest(String model, List<ChatMessage> messages) {}
