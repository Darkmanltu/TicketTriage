package com.example.tickettriage.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.example.tickettriage.model.Comment;
import com.example.tickettriage.model.CommentRequest;
import com.example.tickettriage.model.CommentResponse;
import com.example.tickettriage.service.CommentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/comment")
@Tag(name = "Comments", description = "Restful api for comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    @Operation(summary = "Submit a new comment", description = "Saves the comment and triggers AI triage to decide if it becomes a ticket")
    public CommentResponse uploadComment(@RequestBody CommentRequest req) {
        return commentService.createComment(req);
    }

    @GetMapping
    @Operation(summary = "List all comments")
    public List<Comment> getComments() {
        return commentService.getComments();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a single comment by id")
    public ResponseEntity<Comment> getComment(@PathVariable UUID id) {
        Comment comment = commentService.getCommentById(id);
        if (comment == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(comment);
    }
}