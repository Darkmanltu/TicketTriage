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

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @GetMapping
    public List<String> getItems() {
        return List.of("foo", "bar");
    }

    @PostMapping
    public ResponseEntity<String> createItem(@RequestBody String item) {
        return ResponseEntity.status(HttpStatus.CREATED).body("created: " + item);
    }
}