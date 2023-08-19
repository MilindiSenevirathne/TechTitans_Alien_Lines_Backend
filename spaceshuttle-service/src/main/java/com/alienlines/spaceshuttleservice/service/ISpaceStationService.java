package com.alienlines.spaceshuttleservice.service;


import com.alienlines.spaceshuttleservice.model.SpaceStation;

import java.util.List;

public interface ISpaceStationService {

    public List<SpaceStation> getSpaceStations(String searchText) throws Exception;

}
