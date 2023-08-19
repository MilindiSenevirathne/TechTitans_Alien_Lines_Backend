package com.alienlines.spaceshuttleservice.controller;

import com.alienlines.spaceshuttleservice.model.SpaceStation;
import com.alienlines.spaceshuttleservice.service.ISpaceStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/space-station/")
public class SpaceStationController {

    private final ISpaceStationService spaceStationService;

    @Autowired
    public SpaceStationController(ISpaceStationService spaceStationService) {
        this.spaceStationService = spaceStationService;
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<SpaceStation>> getSpaceStations(@RequestParam(required = false) String searchText) {

        try {
            List<SpaceStation> spaceStationList = spaceStationService.getSpaceStations(searchText);
            return new ResponseEntity<>(spaceStationList, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
