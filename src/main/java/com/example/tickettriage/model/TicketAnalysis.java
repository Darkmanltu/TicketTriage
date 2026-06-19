package com.example.tickettriage.model;

public record TicketAnalysis(
        boolean isTicket,
        String title,
        String category,
        String priority,
        String summary
) {}