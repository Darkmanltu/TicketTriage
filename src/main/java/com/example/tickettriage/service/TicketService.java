package com.example.tickettriage.service;

import java.util.UUID;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.example.tickettriage.model.Ticket;
import com.example.tickettriage.model.Comment;
import com.example.tickettriage.model.TicketAnalysis;
import com.example.tickettriage.model.TicketResponse;
import com.example.tickettriage.repository.TicketRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class TicketService{

    public final TicketRepository ticketRepository;

    public TicketResponse createTicket(Comment comment, TicketAnalysis analysis ){
        Ticket ticket = Ticket.builder()
                .id(comment.getId())
                .title(analysis.title())
                .category(analysis.category())
                .priority(analysis.priority())
                .summary(analysis.summary())
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
      ticketRepository.save(ticket);

        return mapToResponse(ticket);
    }
    public TicketResponse getTicketById(UUID id) {
        if (id == null) {
            return null;
        }
        try {
            Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new Exception("not found"));
            return mapToResponse(ticket);

        } catch (Exception e) {
            log.warn("failed to find ticket {}: {}", id, e.getMessage());
            return null;
        }
    }

    public List<TicketResponse> getTickets() {
        return ticketRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private TicketResponse mapToResponse(Ticket ticket) {
        return new TicketResponse(
                ticket.getId(),
                ticket.getTitle(),
                ticket.getSummary(),
                ticket.getCategory()
        );
    }

}