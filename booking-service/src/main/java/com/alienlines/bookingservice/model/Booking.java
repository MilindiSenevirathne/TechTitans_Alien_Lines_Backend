package com.alienlines.bookingservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "booking")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long user_id;
    private String shuttle_name;
    private String shuttle_type;
    private Integer no_passengers;
    private String from;
    private String to;
    private Date dep_date;
    private Date return_date;
    private Double journey_rate;
    private Double total_price;
    private List<String> services;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "passenger_id", referencedColumnName = "id")
    List < Passenger> passengers = new ArrayList< >();
}
