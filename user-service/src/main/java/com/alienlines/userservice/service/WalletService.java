package com.alienlines.userservice.service;

import com.alienlines.userservice.dto.WalletUpdateReqDTO;
import com.alienlines.userservice.model.Wallet;
import com.alienlines.userservice.repository.WalletRepository;
import com.alienlines.userservice.util.UserNotFoundException;
import com.alienlines.userservice.util.WalletNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class WalletService {
    private final WalletRepository walletRepository;

    public void updateWalletById(Long walletId, WalletUpdateReqDTO updateRequest) throws WalletNotFoundException {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new WalletNotFoundException("Wallet with ID " + walletId + " not found"));

        wallet.setBalance(updateRequest.getBalance());
        wallet.setCurrency(updateRequest.getCurrency());

        walletRepository.save(wallet);
        log.info("Wallet {} updated successfully", walletId);
    }

}
