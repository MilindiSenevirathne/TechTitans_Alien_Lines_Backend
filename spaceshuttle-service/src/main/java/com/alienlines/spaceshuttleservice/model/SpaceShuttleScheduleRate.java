package com.alienlines.spaceshuttleservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "space_shuttle_schedule_rate")
public class SpaceShuttleScheduleRate {


    @Id
    private Long id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "space_shuttle_schedule_id")
    private Long spaceShuttleScheduleId ;
}
