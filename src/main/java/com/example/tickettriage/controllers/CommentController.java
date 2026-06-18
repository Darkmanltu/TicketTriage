package com.example.tickettriage.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import com.example.tickettriage.model.Comment;
import com.example.tickettriage.model.CommentRequest;
import com.example.tickettriage.model.CommentResponse;
import com.example.tickettriage.model.Comment;
import com.example.tickettriage.service.CommentService;

@RestController
@RequestMapping("/api/comment")
@Tag(name = "Comments", description = "Restful api for comments")
@RequiredArgsConstructor
public class CommentController{

    private final CommentService commentService;

    @PostMapping
    public CommentResponse uploadComment(
            @RequestBody CommentRequest req){
        return commentService.createComment(req);
    }



}
