package com.alienlines.destinationservice.service.impl;

import com.alienlines.destinationservice.model.Destination;
import com.alienlines.destinationservice.repository.IDestinationRepository;
import com.alienlines.destinationservice.service.IDestinationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DestinationService implements IDestinationService {

    private final IDestinationRepository destinationRepository;

    @Autowired
    public DestinationService(IDestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    @Override
    public List<Destination> getDestinations() throws Exception {
        try {
            return destinationRepository.findAll();
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Destination getDestinationById(Long destinationId) throws Exception {
        try {
            Optional<Destination> destination = destinationRepository.findById(destinationId);
            if(destination.isPresent()){
                return destination.get();
            } else {
                throw new Exception("Invalid ID");
            }

        }  catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
