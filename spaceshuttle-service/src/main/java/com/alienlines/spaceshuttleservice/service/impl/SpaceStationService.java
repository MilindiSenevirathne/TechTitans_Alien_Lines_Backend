package com.alienlines.spaceshuttleservice.service.impl;

import com.alienlines.spaceshuttleservice.model.SpaceStation;
import com.alienlines.spaceshuttleservice.repository.ISpaceStationRepository;
import com.alienlines.spaceshuttleservice.service.ISpaceStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpaceStationService implements ISpaceStationService {

    private final ISpaceStationRepository spaceStationRepository;

    @Autowired
    public SpaceStationService(ISpaceStationRepository spaceStationRepository) {
        this.spaceStationRepository = spaceStationRepository;
    }

    @Override
    public List<SpaceStation> getSpaceStations(String searchText) throws Exception {
        try {
            if(searchText == null || searchText.isEmpty()){
                return spaceStationRepository.findAll();
            }
            return spaceStationRepository.searchSpaceStations(searchText.toLowerCase());
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
