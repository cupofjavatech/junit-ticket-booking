package com.crif.ticketbooking.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.crif.ticketbooking.model.Ticket;
import com.crif.ticketbooking.util.TicketBookingUtil;

@SpringBootTest
public class TicketServiceTest {

	@Autowired
	private TicketService ticketService;

	@Test
	public void saveTicket_successs() {
		Ticket ticket = TicketBookingUtil.getTicket();
		ticket.setId(0);
		ticket.getFlight().setFlightNo("111");

		ticket.getPassengers().forEach(p -> p.setId(0));
		Ticket ticketReturn = ticketService.saveTicket(ticket);
		assertNotNull(ticketReturn.getId() > 0);
	}

	@Test
	public void ticketPassengerCount_success() {
		int count = ticketService.ticketPassengerCount(1);
		assertEquals(2, count);
	}
}
