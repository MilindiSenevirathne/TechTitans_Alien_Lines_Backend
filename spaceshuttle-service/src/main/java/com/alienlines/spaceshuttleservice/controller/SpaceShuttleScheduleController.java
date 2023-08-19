package com.alienlines.spaceshuttleservice.controller;

//import com.alienlines.spaceshuttleservice.dto.SpaceShuttleSearchDto;
import com.alienlines.spaceshuttleservice.dto.SpaceShuttleSearchDto;
import com.alienlines.spaceshuttleservice.model.SpaceShuttleSchedule;
import com.alienlines.spaceshuttleservice.service.ISpaceShuttleScheduleService;
import com.alienlines.spaceshuttleservice.util.ErrorResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/space-shuttle/")
public class SpaceShuttleScheduleController {

    private final ISpaceShuttleScheduleService spaceShuttleScheduleService;

    @Autowired
    public SpaceShuttleScheduleController(ISpaceShuttleScheduleService spaceShuttleScheduleService) {
        this.spaceShuttleScheduleService = spaceShuttleScheduleService;
    }

    @GetMapping(value = "/{scheduleId}")
    public ResponseEntity<SpaceShuttleSchedule> getSpaceShuttleScheduleById(@PathVariable Long scheduleId) {

        try {
            SpaceShuttleSchedule spaceShuttleSchedule = spaceShuttleScheduleService.getSpaceShuttleScheduleById(scheduleId);
            return new ResponseEntity<>(spaceShuttleSchedule, HttpStatus.OK);
        } catch (Exception ex) {
            return ErrorResponseUtil.errorResponse(ex);
        }
    }

    @GetMapping(value = "/status")
    public ResponseEntity<List<SpaceShuttleSchedule>> getSpaceShuttleStatus(
            @RequestParam(required = false) Boolean isSearchByRoute,
            @RequestParam(required = false) String shuttleId,
            @RequestParam(required = false) String shuttleDate,
            @RequestParam(required = false) Long departureId,
            @RequestParam(required = false) Long arrivalId
    ) {

        try {
            List<SpaceShuttleSchedule> spaceShuttleScheduleList = spaceShuttleScheduleService.getSpaceShuttleStatus(isSearchByRoute, shuttleId, shuttleDate, departureId, arrivalId);
            return new ResponseEntity<>(spaceShuttleScheduleList, HttpStatus.OK);
        } catch (Exception ex) {
            return ErrorResponseUtil.errorResponse(ex);
        }
    }

    @GetMapping(value = "/search")
    public ResponseEntity<SpaceShuttleSearchDto> searchSpaceShuttles(
            @RequestParam(required = false) String shuttleType,
            @RequestParam(required = false) Integer passengerCount,
            @RequestParam(required = false) String departureDate,
            @RequestParam(required = false) Long departureId,
            @RequestParam(required = false) String arrivalDate,
            @RequestParam(required = false) Long arrivalId
    ) {

        try {
            SpaceShuttleSearchDto spaceShuttleSearchDto = spaceShuttleScheduleService.searchSpaceShuttles(shuttleType, passengerCount, departureDate, departureId, arrivalDate, arrivalId);
            return new ResponseEntity<>(spaceShuttleSearchDto, HttpStatus.OK);
        } catch (Exception ex) {
            return ErrorResponseUtil.errorResponse(ex);
        }
    }
}
