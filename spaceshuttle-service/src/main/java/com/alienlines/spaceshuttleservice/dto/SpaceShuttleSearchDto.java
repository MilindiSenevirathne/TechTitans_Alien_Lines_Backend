package com.alienlines.spaceshuttleservice.dto;

import com.alienlines.spaceshuttleservice.model.SpaceShuttleSchedule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpaceShuttleSearchDto {

    private List<SpaceShuttleSchedule> departureList;
    private List<SpaceShuttleSchedule> arrivalList;
}
