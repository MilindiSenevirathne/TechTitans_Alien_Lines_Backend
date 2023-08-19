package com.alienlines.destinationservice.controller;

import com.alienlines.destinationservice.model.Destination;
import com.alienlines.destinationservice.service.IDestinationService;
import com.alienlines.destinationservice.util.ErrorResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/destination/")
public class DestinationController {

    private final IDestinationService destinationService;

    @Autowired
    public DestinationController(IDestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<Destination>> getDestinations() {

        try {
            List<Destination> destinationList = destinationService.getDestinations();
            return new ResponseEntity<>(destinationList, HttpStatus.OK);
        } catch (Exception ex) {
            return ErrorResponseUtil.errorResponse(ex);
        }
    }

    @GetMapping(value = "/{destinationId}")
    public ResponseEntity<Destination> getDestinationById(@PathVariable Long destinationId) {

        try {
            Destination destination = destinationService.getDestinationById(destinationId);
            return new ResponseEntity<>(destination, HttpStatus.OK);
        } catch (Exception ex) {
            return ErrorResponseUtil.errorResponse(ex);
        }
    }
}
