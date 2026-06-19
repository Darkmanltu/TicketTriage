package com.example.tickettriage.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.example.tickettriage.model.Comment;
import com.example.tickettriage.model.CommentRequest;
import com.example.tickettriage.model.CommentResponse;
import com.example.tickettriage.model.TicketAnalysis;
import com.example.tickettriage.repository.CommentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final HuggingFaceService huggingFaceService;
    private final TicketService ticketService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final String SYSTEM_PROMPT = """
        You triage customer comments into support tickets.
        Respond with ONLY a JSON object, no other text, matching exactly this shape:
        {"isTicket": boolean, "title": string, "category": "bug"|"feature"|"billing"|"account"|"other",
         "priority": "low"|"medium"|"high", "summary": string}
        If isTicket is false, set title, category, priority and summary to empty strings.
        """;

    public TicketAnalysis parse(String commentText) {
        String raw = huggingFaceService.ask(SYSTEM_PROMPT, commentText);
        String json = extractJson(raw);
        try {
            return objectMapper.readValue(json, TicketAnalysis.class);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Model returned malformed JSON: " + raw, e);
        }
    }

    private String extractJson(String raw) {
        int start = raw.indexOf('{');
        int end = raw.lastIndexOf('}');
        if (start == -1 || end == -1 || end < start) {
            throw new IllegalStateException("No JSON object found in model response: " + raw);
        }
        return raw.substring(start, end + 1);
    }

    public CommentResponse createComment(CommentRequest req) {

        CommentResponse resp = new CommentResponse();
        Comment comment = new Comment();
        comment.setComment(req.comment);
        comment.setChannel(req.channel);
        comment.setCreatedAt(new Date());
        comment.setUpdatedAt(comment.getCreatedAt());

        try {
            commentRepository.save(comment);
        } catch (Exception e) {
            log.warn("failed to save {} {}", comment.getId(), e.getMessage());
        }

        try {
            TicketAnalysis analysis = parse(comment.getComment());
            if (analysis.isTicket()) {
                ticketService.createTicket(comment, analysis);
            }
        } catch (Exception e) {
            log.warn("failed to get a model response for {}: {}", comment.getId(), e.getMessage());
        }

        resp.id = comment.getId();
        return resp;
    }

    public Comment getCommentById(UUID id) {
        if (id == null) {
            return null;
        }
        try {
            return commentRepository.findById(id).orElseThrow(() -> new Exception("not found"));
        } catch (Exception e) {
            log.warn("failed to find comment {}: {}", id, e.getMessage());
            return null;
        }
    }

    public List<Comment> getComments() {
        return commentRepository.findAll();
    }
}