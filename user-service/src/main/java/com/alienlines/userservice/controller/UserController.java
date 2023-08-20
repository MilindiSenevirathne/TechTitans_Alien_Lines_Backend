package com.alienlines.userservice.controller;

import com.alienlines.userservice.dto.RegisterUser;
import com.alienlines.userservice.dto.UpdateUserRequest;
import com.alienlines.userservice.dto.WalletInfoDTO;
import com.alienlines.userservice.dto.WalletUpdateReqDTO;
import com.alienlines.userservice.model.User;
import com.alienlines.userservice.service.UserService;
import com.alienlines.userservice.util.UserNotFoundException;
import com.alienlines.userservice.util.WalletNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String registerUser(@RequestBody RegisterUser registerUser){
        userService.registerUser(registerUser);
        return "User created Successfully!";
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteUser(@PathVariable Long userId) {
        try {
            userService.deleteUser(userId);
            return "User and associated wallet deleted Successfully!";
        } catch (UserNotFoundException e) {
            return e.toString();
        }
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public String updateUser(@PathVariable Long userId, @RequestBody UpdateUserRequest updateUserRequest) {
        try {
            userService.updateUser(userId, updateUserRequest);
            return "User updated successfully!";
        } catch (UserNotFoundException e) {
            return "User not found with provided ID";
        }
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        try {
            User user = userService.getUserById(userId);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{userId}/wallet")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<WalletInfoDTO> getWalletInfoByUserId(@PathVariable Long userId) {
        try {
            WalletInfoDTO walletInfo = userService.getWalletInfoByUserId(userId);
            return ResponseEntity.ok(walletInfo);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/{userId}/wallet")
    public ResponseEntity<String> updateWalletByUserId(@PathVariable Long userId, @RequestBody WalletUpdateReqDTO updateRequest) {
        try {
            userService.updateWalletbyUserId(userId, updateRequest);
            return ResponseEntity.ok("Wallet updated successfully");
        } catch (UserNotFoundException | WalletNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
