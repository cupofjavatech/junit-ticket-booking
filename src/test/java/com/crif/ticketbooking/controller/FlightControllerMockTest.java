package com.crif.ticketbooking.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.crif.ticketbooking.model.Flight;
import com.crif.ticketbooking.service.FlightService;
import com.crif.ticketbooking.util.TicketBookingUtil;

@SpringBootTest
public class FlightControllerMockTest {

	@Mock
	private FlightService flightService;

	@InjectMocks
	private FlightController flightController = new FlightController(flightService);

	@Test
	public void getAllFlights_list_null() {
		when(flightService.getAllFlights()).thenReturn(null);
		ResponseEntity<List<Flight>> responseObject = flightController.getAllFlights();
		assertNotNull(responseObject);
		assertNotNull(responseObject.getBody());
		assertEquals(HttpStatus.OK, responseObject.getStatusCode());
		assertTrue(responseObject.getBody().size() == 0);
	}

	@Test
	public void getAllFlights_list_empty_array() {
		when(flightService.getAllFlights()).thenReturn(new ArrayList<>());
		ResponseEntity<List<Flight>> responseObject = flightController.getAllFlights();
		assertNotNull(responseObject);
		assertNotNull(responseObject.getBody());
		assertEquals(HttpStatus.OK, responseObject.getStatusCode());
		assertTrue(responseObject.getBody().size() == 0);
	}

	@Test
	public void getAllFlights_succes() {
		when(flightService.getAllFlights()).thenReturn(Arrays.asList(TicketBookingUtil.getFlight()));
		ResponseEntity<List<Flight>> responseObject = flightController.getAllFlights();
		assertEquals(1, responseObject.getBody().get(0).getId());
		assertEquals("test-111", responseObject.getBody().get(0).getFlightNo());
		assertTrue(responseObject.getBody().size() == 1);
	}

	@Test
	public void getFlightByFlightNumber_flightNo_null() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> flightController.getFlightByFlightNumber(null));
		assertEquals("Invalid Value", exception.getMessage());
	}

	@Test
	public void getFlightByFlightNumber_flightNo_empty_string() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> flightController.getFlightByFlightNumber(""));
		assertEquals("Invalid Value", exception.getMessage());
	}

	@Test
	public void getFlightByFlightNumber_flightNo_listempty_string() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> flightController.getFlightByFlightNumber("    "));
		assertEquals("Invalid Value", exception.getMessage());
	}

	@Test
	public void getFlightByFlightNumber_success() {
		when(flightService.getFlightByFlightNumber(anyString())).thenReturn(TicketBookingUtil.getFlight());
		ResponseEntity<Flight> responseObject = flightController.getFlightByFlightNumber("113");
		assertNotNull(responseObject.getBody());
		assertEquals(1, responseObject.getBody().getId());
		assertEquals(100, responseObject.getBody().getCapacity());
	}

	@Test
	public void getFlightFromTo_from_null() {
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
				() -> flightController.getFlightFromTo(null, "abc", "abc"));
		assertNotNull(e);
		assertEquals("Invalid From Value", e.getMessage());
	}

	@Test
	public void getFlightFromTo_Success() {
		when(flightService.getFlightFromTo(anyString(), anyString(), anyString()))
				.thenReturn(Arrays.asList(TicketBookingUtil.getFlight()));
		ResponseEntity<List<Flight>> returnList = flightController.getFlightFromTo("from", "to", "date");
		assertNotNull(returnList);
		assertTrue(returnList.getBody().size() == 1);
		assertEquals("test-111", returnList.getBody().get(0).getFlightNo());
	}
}
