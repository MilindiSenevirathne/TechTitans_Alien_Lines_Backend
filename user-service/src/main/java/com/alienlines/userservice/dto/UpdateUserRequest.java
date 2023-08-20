package com.alienlines.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserRequest {
    private String img_url;
    private String name;
    private String surename;
    private Date dob;
    private String email;
    private String phone_no;
    private String address;
}
