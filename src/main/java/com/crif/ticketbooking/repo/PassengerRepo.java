package com.crif.ticketbooking.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crif.ticketbooking.model.Passenger;

public interface PassengerRepo extends JpaRepository<Passenger, Integer> {

}
