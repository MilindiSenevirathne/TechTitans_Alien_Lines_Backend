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
@Table(name = "space_shuttle")
public class SpaceShuttle {


    @Id
    private String id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "shuttle_type", length = 100)
    private String shuttleType;

    @Column(name = "max_capacity")
    private Integer maxCapacity;

    @Column(name = "image_url", length = 250)
    private String imageUrl ;
}
