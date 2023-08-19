package com.alienlines.userservice.service;

import com.alienlines.userservice.dao.RegisterUser;
import com.alienlines.userservice.model.User;
import com.alienlines.userservice.model.Wallet;
import com.alienlines.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

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

        log.info("Wallet {} is Created", wallet.getId());
        log.info("User {} is saved", user.getId());
    }

}
