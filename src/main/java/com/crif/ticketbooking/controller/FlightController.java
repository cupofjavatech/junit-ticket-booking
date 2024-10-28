package com.crif.ticketbooking.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crif.ticketbooking.model.Flight;
import com.crif.ticketbooking.service.FlightService;
import com.google.common.base.Preconditions;

@RestController
@RequestMapping(path = "/flight")
public class FlightController {

	private FlightService flightService;

	public FlightController(FlightService flightService) {
		this.flightService = flightService;
	}

	@GetMapping("/all")
	public ResponseEntity<List<Flight>> getAllFlights() {
		List<Flight> list = flightService.getAllFlights();
		return list != null ? ResponseEntity.ok(list) : ResponseEntity.ok(new ArrayList<>());
	}

	@GetMapping("/flightnumber/{flightNumber}")
	public ResponseEntity<Flight> getFlightByFlightNumber(@PathVariable("flightNumber") String flightNumber) {
		Preconditions.checkArgument(Strings.isNotBlank(flightNumber), "Invalid Value"); //
		return ResponseEntity.ok(flightService.getFlightByFlightNumber(flightNumber));
	}

	@GetMapping("/from/{from}/to/{to}/date/{date}")
	public ResponseEntity<List<Flight>> getFlightFromTo(@PathVariable("from") String from,
			@PathVariable("to") String to, @PathVariable("date") String date) {
		Preconditions.checkArgument(Strings.isNotBlank(from), "Invalid From Value");
		Preconditions.checkArgument(Strings.isNotBlank(to), "Invalid To value");

		return ResponseEntity.ok(flightService.getFlightFromTo(from, to, date));
	}

	@PostMapping("/update/{flightNumber}")
	public ResponseEntity<Flight> updateFlight(@RequestBody Flight flight,
			@PathVariable("flightNumber") String flightNumber) {
		Preconditions.checkArgument(flight != null, "Invalid Flight Value");
		Preconditions.checkArgument(flightNumber != null, "Invalid Flight Id");
		return ResponseEntity.ok(flightService.updateFlight(flight, flightNumber));
	}

}
