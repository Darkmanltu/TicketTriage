package com.example.tickettriage.model;
import lombok.AllArgsConstructor;
import java.util.UUID;
import java.util.Date;
@AllArgsConstructor
public class TicketResponse {
    public UUID id;
    public String title;
    public String summary;
    public String category;

}