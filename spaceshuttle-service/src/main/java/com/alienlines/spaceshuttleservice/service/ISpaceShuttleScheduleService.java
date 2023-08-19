package com.alienlines.spaceshuttleservice.service;


import com.alienlines.spaceshuttleservice.dto.SpaceShuttleSearchDto;
import com.alienlines.spaceshuttleservice.model.SpaceShuttleSchedule;

import java.util.List;

public interface ISpaceShuttleScheduleService {

    public List<SpaceShuttleSchedule> getSpaceShuttleStatus(Boolean isSearchByRoute, String shuttleId, String shuttleDate, Long destinationId, Long arrivalId) throws Exception;

    public SpaceShuttleSearchDto searchSpaceShuttles(String shuttleType, Integer passengerCount, String departureDate, Long destinationId, String arrivalDate, Long arrivalId) throws Exception;

    public SpaceShuttleSchedule getSpaceShuttleScheduleById(Long scheduleId) throws Exception;
}
