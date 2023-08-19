package com.alienlines.destinationservice.service;


import com.alienlines.destinationservice.model.Destination;

import java.util.List;

public interface IDestinationService {

    public List<Destination> getDestinations() throws Exception;

    public Destination getDestinationById(Long destinationId) throws Exception;

}
