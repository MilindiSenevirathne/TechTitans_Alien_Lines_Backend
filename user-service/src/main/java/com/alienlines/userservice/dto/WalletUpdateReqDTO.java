package com.alienlines.userservice.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WalletUpdateReqDTO {
    private Double balance;
    private String currency;
}
