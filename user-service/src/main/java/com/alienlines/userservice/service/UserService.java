package com.alienlines.userservice.service;

import com.alienlines.userservice.dto.RegisterUser;
import com.alienlines.userservice.dto.UpdateUserRequest;
import com.alienlines.userservice.dto.WalletInfoDTO;
import com.alienlines.userservice.model.User;
import com.alienlines.userservice.model.Wallet;
import com.alienlines.userservice.repository.UserRepository;
import com.alienlines.userservice.repository.WalletRepository;
import com.alienlines.userservice.util.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;

    public void registerUser(RegisterUser registerUser){

        User user = new User();
        Wallet wallet = new Wallet();

        user.setImg_url(registerUser.getImg_url());
        user.setName(registerUser.getName());
        user.setSurename(registerUser.getSurename());
        user.setDob(registerUser.getDob());
        user.setEmail(registerUser.getEmail());
        user.setPhone_no(registerUser.getPhone_no());
        user.setAddress(registerUser.getAddress());

        wallet.setBalance(registerUser.getBalance());
        wallet.setCurrency(registerUser.getCurrency());

        user.setWallet(wallet);
        wallet.setUser(user);

        userRepository.save(user);
        walletRepository.save(wallet);

        log.info("Wallet {} is Created", wallet.getId());
        log.info("User {} is saved", user.getId());
    }

    public void deleteUser(Long userId) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Before deleting the user, make sure to delete associated entities
            Wallet wallet = user.getWallet();
            if (wallet != null) {
                walletRepository.delete(wallet);
            }

            userRepository.delete(user);
            log.info("User {} and associated wallet are deleted", userId);
        } else {
            throw new UserNotFoundException("User with ID " + userId + " not found");
        }
    }

    public void updateUser(Long userId, UpdateUserRequest updateUserRequest) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Update fields from the DAO
            user.setImg_url(updateUserRequest.getImg_url());
            user.setName(updateUserRequest.getName());
            user.setSurename(updateUserRequest.getSurename());
            user.setDob(updateUserRequest.getDob());
            user.setEmail(updateUserRequest.getEmail());
            user.setPhone_no(updateUserRequest.getPhone_no());
            user.setAddress(updateUserRequest.getAddress());

            userRepository.save(user);
            log.info("User {} updated successfully", userId);
        } else {
            throw new UserNotFoundException("User with ID " + userId + " not found");
        }
    }

    public User getUserById(Long userId) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new UserNotFoundException("User with ID " + userId + " not found");
        }
    }

    public WalletInfoDTO getWalletInfoByUserId(Long userId) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Wallet wallet = user.getWallet();

            if (wallet != null) {
                WalletInfoDTO walletInfoDTO = new WalletInfoDTO();
                walletInfoDTO.setWalletId(wallet.getId());
                walletInfoDTO.setBalance(wallet.getBalance());
                walletInfoDTO.setCurrency(wallet.getCurrency());
                return walletInfoDTO;
            } else {
                throw new IllegalStateException("User with ID " + userId + " has no associated wallet");
            }
        } else {
            throw new UserNotFoundException("User with ID " + userId + " not found");
        }
    }

}
