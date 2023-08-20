package com.alienlines.userservice.controller;

import com.alienlines.userservice.dto.WalletUpdateReqDTO;
import com.alienlines.userservice.service.WalletService;
import com.alienlines.userservice.util.WalletNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wallet")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @PutMapping("/{walletId}")
    public ResponseEntity<String> updateWalletById(@PathVariable Long walletId, @RequestBody WalletUpdateReqDTO updateRequest) {
        try {
            walletService.updateWalletById(walletId, updateRequest);
            return ResponseEntity.ok("Wallet updated successfully");
        } catch (WalletNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
