package com.crif.ticketbooking.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.crif.ticketbooking.exception.RecordNotException;
import com.crif.ticketbooking.exception.TicketException;
import com.crif.ticketbooking.model.Ticket;
import com.crif.ticketbooking.repo.FlightRepo;
import com.crif.ticketbooking.repo.PassengerRepo;
import com.crif.ticketbooking.repo.TicketRepo;
import com.crif.ticketbooking.util.TicketBookingUtil;

@SpringBootTest
public class TIcketServiceMockTest {
	@Mock
	private TicketRepo ticketRepo;

	@Mock
	private FlightRepo flightRepo;

	@Mock
	private PassengerRepo passengerRepo;

	@InjectMocks
	private TicketService ticketService = new TicketService(ticketRepo, flightRepo, passengerRepo);

	@Test
	public void getTicket_empty_optional() {
		when(ticketRepo.findById(anyInt())).thenReturn(Optional.empty());
		RecordNotException exception = assertThrows(RecordNotException.class, () -> ticketService.getTicket(0));
		assertNotNull(exception);
		assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
	}

	@Test
	public void getTicket_success() {
		when(ticketRepo.findById(anyInt())).thenReturn(Optional.of(TicketBookingUtil.getTicket()));
		Ticket ticket = ticketService.getTicket(1);
		assertNotNull(ticket);
		assertEquals(1, ticket.getId());
		assertEquals("test-111", ticket.getFlight().getFlightNo());
		ticket.getPassengers().stream().forEach((f) -> {
			assertEquals("Passenger 01", f.getName());
		});
	}

	@Test
	public void saveTicket_flight_null() {
		TicketException exception = assertThrows(TicketException.class, () -> ticketService.saveTicket(null));
		assertEquals("Error While Saving Ticket", exception.getMessage());
	}

	@Test
	public void saveTicket_ticetksave_exception() {
		when(flightRepo.findByFlightNo(anyString())).thenReturn(TicketBookingUtil.getFlight());
		when(ticketRepo.save(any())).thenThrow(new RuntimeException("Error"));
		TicketException exception = assertThrows(TicketException.class,
				() -> ticketService.saveTicket(TicketBookingUtil.getTicket()));
		assertEquals("Error While Saving Ticket", exception.getMessage());

	}
}
