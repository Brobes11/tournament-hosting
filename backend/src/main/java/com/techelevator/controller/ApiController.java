package com.techelevator.controller;

import com.techelevator.authentication.AuthProvider;
import com.techelevator.authentication.UnauthorizedException;
import com.techelevator.model.User;
import com.techelevator.model.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * ApiController
 */
@CrossOrigin
@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private AuthProvider authProvider;

    @Autowired
    private UserDao userDao;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String authorizedOnly() throws UnauthorizedException {
        /*
         * You can lock down which roles are allowed by checking if the current user has
         * a role.
         * 
         * In this example, if the user does not have the admin or user role we send
         * back an unauthorized error.
         */
        if (!authProvider.userHasRole(new String[] { "admin", "user" })) {
            throw new UnauthorizedException();
        }
        return "Success";
    }

    @GetMapping(path="/user/{username}")
    public User getUser(@PathVariable String username) {
        return userDao.getUserByUsername(username);
    }
    
}