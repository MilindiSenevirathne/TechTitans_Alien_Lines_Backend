package com.alienlines.spaceshuttleservice.service.impl;

import com.alienlines.spaceshuttleservice.dto.SpaceShuttleSearchDto;
import com.alienlines.spaceshuttleservice.model.SpaceShuttleSchedule;
import com.alienlines.spaceshuttleservice.repository.ISpaceShuttleScheduleRepository;
import com.alienlines.spaceshuttleservice.service.ISpaceShuttleScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;


@Service
public class SpaceShuttleScheduleService implements ISpaceShuttleScheduleService {

    private final ISpaceShuttleScheduleRepository spaceShuttleScheduleRepository;

    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    public SpaceShuttleScheduleService(ISpaceShuttleScheduleRepository spaceShuttleScheduleRepository) {
        this.spaceShuttleScheduleRepository = spaceShuttleScheduleRepository;
    }

    @Override
    public List<SpaceShuttleSchedule> getSpaceShuttleStatus(Boolean isSearchByRoute, String shuttleId, String shuttleDate, Long departureId, Long arrivalId) throws Exception {

        if(isSearchByRoute){
            if(departureId == null){
                throw new Exception("Departure ID is required.");
            }

            if(arrivalId == null){
                throw new Exception("Arrival ID is required.");
            }
        } else {
            if(shuttleId == null || shuttleId.isEmpty()){
                throw new Exception("Shuttle ID is required.");
            }
        }

        if(shuttleDate == null || shuttleDate.isEmpty()){
            throw new Exception("Date is required.");
        }

        try {
            LocalDate parsedDate = LocalDate.parse(shuttleDate, dateFormatter);

            if(isSearchByRoute){
                return spaceShuttleScheduleRepository.searchSpaceShuttleSchedulesByRoute(parsedDate.format(dateFormatter), departureId, arrivalId);
            }
            return spaceShuttleScheduleRepository.searchSpaceShuttleSchedulesByFlight(shuttleId, parsedDate.format(dateFormatter));
        } catch (DateTimeParseException e) {
            throw new Exception("Use correct format (YYYY-MM-DD) for shuttle date.");
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public SpaceShuttleSearchDto searchSpaceShuttles(String shuttleType, Integer passengerCount, String departureDate, Long departureId, String arrivalDate, Long arrivalId) throws Exception {

        if(shuttleType == null || shuttleType.isEmpty()){ throw new Exception("Shuttle Type is required.");}
        if(passengerCount == null){ throw new Exception("Passenger Count is required.");}
        if(departureId == null){ throw new Exception("Departure Station ID is required.");}
        if(arrivalId == null){ throw new Exception("Arrival Station ID is required.");}
        if(departureDate == null || departureDate.isEmpty()){ throw new Exception("Departure Date is required.");}
        if(arrivalDate == null || arrivalDate.isEmpty()){ throw new Exception("Arrival Date is required.");}

        try {
            LocalDate parsedDepartureDate = LocalDate.parse(departureDate, dateFormatter);
            LocalDate parsedArrivalDate = LocalDate.parse(arrivalDate, dateFormatter);

            SpaceShuttleSearchDto spaceShuttleSearchDto = new SpaceShuttleSearchDto();

            List<SpaceShuttleSchedule> departureSpaceShuttleScheduleList = spaceShuttleScheduleRepository.searchSpaceShuttleSchedules(shuttleType, passengerCount, parsedDepartureDate.format(dateFormatter), departureId, arrivalId);
            List<SpaceShuttleSchedule> arrivalSpaceShuttleScheduleList = spaceShuttleScheduleRepository.searchSpaceShuttleSchedules(shuttleType, passengerCount, parsedArrivalDate.format(dateFormatter), arrivalId, departureId);

            spaceShuttleSearchDto.setDepartureList(departureSpaceShuttleScheduleList);
            spaceShuttleSearchDto.setArrivalList(arrivalSpaceShuttleScheduleList);

            return spaceShuttleSearchDto;
        } catch (DateTimeParseException e) {
            throw new Exception("Use correct format (YYYY-MM-DD) for dates.");
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public SpaceShuttleSchedule getSpaceShuttleScheduleById(Long scheduleId) throws Exception {
        try {
            Optional<SpaceShuttleSchedule> spaceShuttleSchedule = spaceShuttleScheduleRepository.findById(scheduleId);
            if(spaceShuttleSchedule.isPresent()){
                return spaceShuttleSchedule.get();
            } else {
                throw new Exception("Invalid ID");
            }

        }  catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
