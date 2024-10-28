package com.crif.ticketbooking.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.crif.ticketbooking.model.Flight;

public interface FlightRepo extends JpaRepository<Flight, Integer> {

	@Override
	@Query(value = "SELECT f FROM Flight f")
	List<Flight> findAll();

	@Query(value = "SELECT f FROM Flight f WHERE flightNo = :flightNo")
	Flight findByFlightNo(String flightNo);

	@Query(value = "SELECT f FROM Flight f WHERE fromStation=:from AND toStation=:to")
	List<Flight> findFromTo(String from, String to);

}
