package com.alienlines.spaceshuttleservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "space_shuttle_schedule")
public class SpaceShuttleSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "departure_date_time")
    private LocalDateTime departureDateTime;

    @Column(name = "arrival_date_time")
    private LocalDateTime arrivalDateTime;

    @ManyToOne
    @JoinColumn(name = "shuttle_id", nullable = false, referencedColumnName = "id")
    private SpaceShuttle shuttleId;

    @ManyToOne
    @JoinColumn(name = "departure_station_id", nullable = false, referencedColumnName = "id")
    private SpaceStation departureStationId;

    @ManyToOne
    @JoinColumn(name = "arrival_station_id", nullable = false, referencedColumnName = "id")
    private SpaceStation arrivalStationId;

    @OneToMany
    @JoinColumn(name = "space_shuttle_schedule_id", insertable = false, updatable = false)
    private List<SpaceShuttleScheduleRate> spaceShuttleScheduleRates;

    @OneToMany
    @JoinColumn(name = "space_shuttle_schedule_id", insertable = false, updatable = false)
    private List<SpaceShuttleScheduleService> spaceShuttleScheduleServices;
}
