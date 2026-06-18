package com.example.tickettriage.service;

import java.util.UUID;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.example.tickettriage.model.Comment;
import com.example.tickettriage.model.TicketResponse;
import com.example.tickettriage.repository.TicketRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class TicketService{

    public final TicketRepository ticketRepository;

    public TicketResponse createTicket(Comment comment, String title, String category, String priority ){

        TicketResponse resp = new TicketResponse();
        resp.id = comment.getId();
        resp.title = ""; // for now
        resp.summary = "";
        resp.category = "";

//        ticketRepository.save();  need to adjust after getting the stuff from the model
        return resp;
    }

}