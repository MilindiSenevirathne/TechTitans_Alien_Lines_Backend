package com.alienlines.spaceshuttleservice.repository;

import com.alienlines.spaceshuttleservice.model.SpaceShuttleSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ISpaceShuttleScheduleRepository extends JpaRepository<SpaceShuttleSchedule, Long> {

    @Query(value = "SELECT * FROM space_shuttle_schedule WHERE shuttle_id = :shuttleId AND CAST(departure_date_time AS VARCHAR) LIKE %:shuttleDate%", nativeQuery = true)
    List<SpaceShuttleSchedule> searchSpaceShuttleSchedulesByFlight(String shuttleId, String shuttleDate);

    @Query(value = "SELECT * FROM space_shuttle_schedule WHERE departure_station_id = :destinationId AND arrival_station_id = :arrivalId AND CAST(departure_date_time AS VARCHAR) LIKE %:shuttleDate%", nativeQuery = true)
    List<SpaceShuttleSchedule> searchSpaceShuttleSchedulesByRoute(String shuttleDate, Long destinationId, Long arrivalId);

    @Query(value = "SELECT SSS.id, SSS.departure_date_time, SSS.arrival_date_time, SSS.shuttle_id, SSS.departure_station_id, SSS.arrival_station_id FROM space_shuttle_schedule SSS JOIN space_shuttle SS ON SSS.shuttle_id = SS.id WHERE SS.shuttle_type = :shuttleType AND SS.max_capacity >= :passengerCount AND SSS.departure_station_id = :destinationId AND SSS.arrival_station_id = :arrivalId AND CAST(SSS.departure_date_time AS VARCHAR) LIKE %:shuttleDate%", nativeQuery = true)
//    @Query(value = "SELECT sss FROM SpaceShuttleSchedule sss, SpaceShuttle ss WHERE ss.shuttleType = :shuttleType AND ss.maxCapacity >= :passengerCount AND sss.departureStationId = :destinationId AND sss.arrivalStationId = :arrivalId AND CAST(sss.departureDateTime AS String) LIKE %:shuttleDate%")
    List<SpaceShuttleSchedule> searchSpaceShuttleSchedules(String shuttleType, Integer passengerCount, String shuttleDate, Long destinationId, Long arrivalId);
}
