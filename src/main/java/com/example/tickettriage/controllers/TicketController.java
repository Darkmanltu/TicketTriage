package com.example.tickettriage.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.example.tickettriage.model.Ticket;
import com.example.tickettriage.model.TicketResponse;
import com.example.tickettriage.service.TicketService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tickets")
@Tag(name = "Tickets", description = "Restful api for tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @GetMapping
    @Operation(summary = "List all tickets")
    public List<TicketResponse> getTickets() {
        return ticketService.getTickets();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a single ticket by id")
    public ResponseEntity<TicketResponse> getTicket(@PathVariable UUID id) {
        TicketResponse ticket = ticketService.getTicketById(id);
        if (ticket == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ticket);
    }
}