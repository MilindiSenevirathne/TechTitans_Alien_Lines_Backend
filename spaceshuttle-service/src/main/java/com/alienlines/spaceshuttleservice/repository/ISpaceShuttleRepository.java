package com.alienlines.spaceshuttleservice.repository;

import com.alienlines.spaceshuttleservice.model.SpaceShuttle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISpaceShuttleRepository extends JpaRepository<SpaceShuttle, Long> {
}
