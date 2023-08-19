package com.alienlines.bookingservice.repository;

import com.alienlines.bookingservice.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
