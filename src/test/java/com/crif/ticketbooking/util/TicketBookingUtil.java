package com.crif.ticketbooking.util;

import java.util.HashSet;
import java.util.Set;

import com.crif.ticketbooking.model.Flight;
import com.crif.ticketbooking.model.Passenger;
import com.crif.ticketbooking.model.Ticket;

public class TicketBookingUtil {

	public static Flight getFlight() {
		Flight flight = new Flight();
		flight.setId(1);
		flight.setFlightNo("test-111");
		flight.setCapacity(100);
//		Set<Ticket> tickets = new HashSet<>();
//		tickets.add(getTicket());
//
//		flight.setTickets(tickets);
		return flight;
	}

	public static Passenger getPassenger() {
		Passenger passenger = new Passenger();
		passenger.setId(1);
		passenger.setAge(25);
		passenger.setName("Passenger 01");
		passenger.setNationality("Indian");
		passenger.setPassportNo("IND-BE2330123");
		return passenger;
	}

	public static Ticket getTicket() {
		Ticket ticket = new Ticket();
		ticket.setId(1);
		ticket.setFlight(getFlight());
		Set<Passenger> passengers = new HashSet<>();
		passengers.add(getPassenger());
		ticket.setPassengers(passengers);
		return ticket;
	}
}
