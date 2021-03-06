package com.techelevator.controller;

import javax.validation.Valid;

import com.techelevator.authentication.AuthProvider;
import com.techelevator.authentication.JwtTokenHandler;
import com.techelevator.authentication.RegistrationResult;
import com.techelevator.authentication.UnauthorizedException;
import com.techelevator.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

/**
 * AccountController
 */
@CrossOrigin
@RestController
public class AccountController {
    @Autowired
    private AuthProvider auth;

    @Autowired
    private JwtTokenHandler tokenHandler;

    @PostMapping("/login")
    public String login(@RequestBody User user) throws UnauthorizedException {
        if (auth.signIn(user.getUsername(), user.getPassword())) {
            User currentUser = auth.getCurrentUser();
            return tokenHandler.createToken(currentUser);
        } else {
            throw new UnauthorizedException();
        }
    }

    @PostMapping("/register")
    public RegistrationResult register(@Valid @RequestBody User user, BindingResult result) {
        RegistrationResult registrationResult = new RegistrationResult();
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                registrationResult.addError(error.getDefaultMessage());
            }
        } else {
            User createdUser = auth.register(user.getUsername(), user.getFirstName(), user.getLastName(),
                    user.getEmail(), user.getPassword(), user.getRole());
            if (createdUser != null) {
                registrationResult.setSuccess(true);
            } else {
                registrationResult
                        .addError("Username or Email already exists. Please select a different username or email.");
            }
        }
        return registrationResult;
    }

}