package com.example.tickettriage.model;

import java.util.UUID;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue
    private UUID id;
    //maybe cool to use uuidv7 for showing creative and up to date on efficiency
    // but will use h2 memory database so might be overkill

    @Column(name = "comment")
    private String comment;

    //diffrent channels, prob need to track which ip/ what service used it?
    @Column(name = "channel")
    private String channel;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;


}