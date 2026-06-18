package com.example.tickettriage.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.tickettriage.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, UUID>{

}
