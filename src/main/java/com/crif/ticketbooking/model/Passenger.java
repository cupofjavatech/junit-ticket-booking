package com.crif.ticketbooking.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Passenger {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@Column(name = "NAME", nullable = false)
	String name;

	@Column(name = "PASSPORT_NO")
	String passportNo;

	@Column(name = "AGE", length = 3)
	int age;

	@Column(name = "NATIONALITY")
	String nationality;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "TICKET_ID")
	private Ticket ticket;

	public int getAge() {
		return age;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getNationality() {
		return nationality;
	}

	public String getPassportNo() {
		return passportNo;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	// https://springhow.com/spring-data-jpa-one-to-many/
	@JsonBackReference
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

}
