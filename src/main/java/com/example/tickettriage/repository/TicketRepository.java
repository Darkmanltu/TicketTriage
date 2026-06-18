package com.example.tickettriage.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.tickettriage.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, UUID> {

}