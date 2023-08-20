package com.alienlines.bookingservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
    private Long userId;
    private String shuttle_name;
    private String shuttle_type;
    private Integer no_passengers;
    private String _from;
    private String _to;
    private Date dep_date;
    private Date return_date;
    private Double journey_rate;
    private Double price_extrabaggage;
    private List<String> seats;
    private List<String> meals;
    private List<String> assistance;
    private Double total_price;

    @OneToMany(mappedBy = "booking")
    private List<Passenger> passengers;
}
