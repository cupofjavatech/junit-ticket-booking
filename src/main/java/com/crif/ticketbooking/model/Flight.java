package com.crif.ticketbooking.model;

import java.util.Date;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "FLIGHT")
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "FLIGHT_NO", nullable = false, length = 5)
	private String flightNo;

	@Column(name = "CAPACITY", nullable = false, length = 4)
	private int capacity;

	@Column(name = "FROM_STATION", nullable = false)
	private String fromStation;

	@Column(name = "TO_STATION", nullable = false)
	private String toStation;

	@Column(name = "FROM_CODE")
	private String fromCode;

	@Column(name = "TO_CODE")
	private String toCode;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DEPARTURE")
	private Date departure;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ARRIVAL")
	private Date arrival;

	@Column(name = "PRICE", precision = 2)
	private Double price;

	@OneToMany(mappedBy = "flight", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Ticket> tickets;

	public Boolean bookFlight() {
		if (this.capacity > 0) {
			this.capacity--;
			return true;
		}
		return false;
	}

	public boolean cancelFlight() {
		this.capacity++;
		return true;
	}

	public Date getArrival() {
		return arrival;
	}

	public int getCapacity() {
		return capacity;
	}

	public Date getDeparture() {
		return departure;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public String getFromCode() {
		return fromCode;
	}

	public String getFromStation() {
		return fromStation;
	}

	public int getId() {
		return id;
	}

	public Double getPrice() {
		return price;
	}

	// @JsonBackReference
	public Set<Ticket> getTickets() {
		return tickets;
	}

	public String getToCode() {
		return toCode;
	}

	public String getToStation() {
		return toStation;
	}

	public void setArrival(Date arrival) {
		this.arrival = arrival;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public void setDeparture(Date departure) {
		this.departure = departure;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public void setFromCode(String fromCode) {
		this.fromCode = fromCode;
	}

	public void setFromStation(String fromStation) {
		this.fromStation = fromStation;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setTickets(Set<Ticket> tickets) {
		this.tickets = tickets;
	}

	public void setToCode(String toCode) {
		this.toCode = toCode;
	}

	public void setToStation(String toStation) {
		this.toStation = toStation;
	}
}
