package com.alienlines.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WalletInfoDTO {
    private Long walletId;
    private Double balance;
    private String currency;
}
