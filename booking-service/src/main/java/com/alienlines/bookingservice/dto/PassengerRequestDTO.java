package com.alienlines.bookingservice.dto;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PassengerRequestDTO {
    private String name;
    private String surename;
    private Date dob;
    private String nationality;
    private String doc_type;
    private String doc_no;
    private String email;
    private String phone_no;
}
