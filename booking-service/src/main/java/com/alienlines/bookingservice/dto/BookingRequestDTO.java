package com.alienlines.bookingservice.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookingRequestDTO {
    private Long user_id;
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
    private List<PassengerRequestDTO> passengers;
}
