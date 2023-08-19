package com.alienlines.destinationservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "destination")
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "planet", length = 100)
    private String planet;

    @OneToMany
    @JoinColumn(name = "destination_id", insertable = false, updatable = false)
    private List<DestinationFeature> destinationFeatures;

    @OneToMany
    @JoinColumn(name = "destination_id", insertable = false, updatable = false)
    private List<DestinationImage> destinationImages;
}
