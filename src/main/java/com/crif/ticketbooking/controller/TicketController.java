package com.crif.ticketbooking.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crif.ticketbooking.model.Ticket;
import com.crif.ticketbooking.service.TicketService;
import com.google.common.base.Preconditions;

@RestController
@RequestMapping("/ticket")
public class TicketController {

	private TicketService ticketService;

	public TicketController(TicketService ticketService) {
		this.ticketService = ticketService;
	}

	@PostMapping("/add")
	public Ticket addTicket(@RequestBody Ticket ticket) {

		Preconditions.checkNotNull(ticket, "TIcket Object is null");
		Preconditions.checkNotNull(ticket.getPassengers(), "No Passenger found");
		Preconditions.checkArgument(!ticket.getPassengers().isEmpty(), "No Passenger found");
		Preconditions.checkNotNull(ticket.getFlight(), "Flight not found");
		Preconditions.checkArgument(ticket.getFlight().getFlightNo() != null, "No Flight Selected");
		return ticketService.saveTicket(ticket);
	}

	@GetMapping("/id/{id}/passenger-count")
	public int getPassengerCount(@PathVariable("id") Integer id) {
		Preconditions.checkArgument(id != null && id > 0, "Invalid Value");
		return ticketService.ticketPassengerCount(id);
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<Ticket> getTicketById(@PathVariable("id") Integer id) {
		Preconditions.checkArgument(id != null && id > 0, "Invalid Value");
		return ResponseEntity.ok(ticketService.getTicket(id));
	}
}
