package com.crif.ticketbooking.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.crif.ticketbooking.model.Ticket;

public interface TicketRepo extends JpaRepository<Ticket, Integer> {

	@Query("SELECT count(p) FROM Passenger p WHERE p.ticket.id = :id")
	int ticketPassengerCount(int id);
}
