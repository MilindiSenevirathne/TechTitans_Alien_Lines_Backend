package com.alienlines.spaceshuttleservice.repository;

import com.alienlines.spaceshuttleservice.model.SpaceStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ISpaceStationRepository extends JpaRepository<SpaceStation, Long> {

    @Query(value = "SELECT * FROM space_station WHERE LOWER(name) LIKE %:searchText% or LOWER (planet) LIKE %:searchText%", nativeQuery = true)
    List<SpaceStation> searchSpaceStations(String searchText);
}
