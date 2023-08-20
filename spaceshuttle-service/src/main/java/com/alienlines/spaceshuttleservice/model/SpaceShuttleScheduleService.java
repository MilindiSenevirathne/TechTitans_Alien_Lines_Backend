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
@Table(name = "space_shuttle_schedule_service")
public class SpaceShuttleScheduleService {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "service_name", length = 100)
    private String name;

    @Column(name = "service_description", length = 1000)
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "space_shuttle_schedule_id")
    private Long spaceShuttleScheduleId ;
}
