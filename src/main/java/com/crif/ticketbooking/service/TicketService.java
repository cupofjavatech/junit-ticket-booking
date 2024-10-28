package com.crif.ticketbooking.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.crif.ticketbooking.exception.RecordNotException;
import com.crif.ticketbooking.exception.TicketException;
import com.crif.ticketbooking.model.Flight;
import com.crif.ticketbooking.model.Passenger;
import com.crif.ticketbooking.model.Ticket;
import com.crif.ticketbooking.repo.FlightRepo;
import com.crif.ticketbooking.repo.PassengerRepo;
import com.crif.ticketbooking.repo.TicketRepo;

import jakarta.transaction.Transactional;

@Service
public class TicketService {

	Logger LOG = LoggerFactory.getLogger(TicketService.class);

	private TicketRepo ticketRepo;

	private FlightRepo flightRepo;

	private PassengerRepo passengerRepo;

	public TicketService(TicketRepo ticketRepo, FlightRepo flightRepo, PassengerRepo passengerRepo) {
		this.ticketRepo = ticketRepo;
		this.flightRepo = flightRepo;
		this.passengerRepo = passengerRepo;
	}

	public Ticket getTicket(int id) {
		Optional<Ticket> ticket = ticketRepo.findById(id);
		return ticket.orElseThrow(() -> new RecordNotException("Ticket Not Found", null, HttpStatus.NOT_FOUND));
	}

	@Transactional
	public Ticket saveTicket(Ticket ticket) {

		try {
			Flight flight = flightRepo.findByFlightNo(ticket.getFlight().getFlightNo());

			if (flight == null) {
				throw new RecordNotException("Flight Not Found", null, HttpStatus.NOT_FOUND);
			}
			ticket.setFlight(flight);

			Ticket ticketSave = ticketRepo.save(ticket);

			List<Passenger> passengers = ticket.getPassengers().stream().map(p -> {
				p.setTicket(ticketSave);
				return p;
			}).collect(Collectors.toList());

			passengers = passengerRepo.saveAll(passengers);

			return ticketSave;

		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new TicketException("Error While Saving Ticket", e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public int ticketPassengerCount(int id) {
		return ticketRepo.ticketPassengerCount(id);
	}
}
