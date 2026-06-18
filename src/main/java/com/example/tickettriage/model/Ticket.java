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
public class Ticket {

    // for simplisity same id is for ticket and for comment, and look up is easier
    // but not all comments become tickets
    @Id
    private UUID id;

    @Column
    private String title;

    @Column
    private String category;
    //could be enum
    @Column
    private String priority;

    @Column
    private String summary;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

}