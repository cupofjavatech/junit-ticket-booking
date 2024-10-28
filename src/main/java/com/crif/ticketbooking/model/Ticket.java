package com.crif.ticketbooking.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "TICKET")
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FLIGHT_ID", nullable = false)
	private Flight flight;

	@OneToMany(mappedBy = "ticket", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Passenger> passengers;

	// https://springhow.com/spring-data-jpa-one-to-many/
	@JsonBackReference
	public Flight getFlight() {
		return flight;
	}

	public int getId() {
		return id;
	}

	public Set<Passenger> getPassengers() {
		return passengers;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPassengers(Set<Passenger> passengers) {
		this.passengers = passengers;
	}

}
