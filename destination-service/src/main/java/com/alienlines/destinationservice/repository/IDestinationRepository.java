package com.alienlines.destinationservice.repository;

import com.alienlines.destinationservice.model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDestinationRepository extends JpaRepository<Destination, Long> {
}
