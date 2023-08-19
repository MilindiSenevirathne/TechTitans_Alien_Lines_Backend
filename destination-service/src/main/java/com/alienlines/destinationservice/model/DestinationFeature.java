package com.alienlines.destinationservice.model;

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
@Table(name = "destination_feature")
public class DestinationFeature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "feature_name", length = 100)
    private String featureName;

    @Column(name = "feature_description", length = 100)
    private String featureDescription;

    @Column(name = "destination_id")
    private Long destinationId ;
}
