package com.revature.controllers;


import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.util.JwtConfig;
import com.revature.util.JwtGenerator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private static Logger log = Logger.getLogger(UserController.class);
    private UserService service;

    @Autowired
    public UserController(UserService userService) {
        this.service = userService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/auth", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public User login(@RequestBody User user, HttpServletResponse resp) {

        User authUser = service.getByCredentials(user.getUsername(), user.getPassword());
        if (authUser == null) {
            return null;
        }

        resp.addHeader(JwtConfig.HEADER, JwtConfig.PREFIX + JwtGenerator.createJwt(authUser));
        resp.addHeader("user_id", Integer.toString(authUser.getUser_id()));
        resp.addHeader("username", authUser.getUsername());
        return authUser;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public User registerUser(@RequestBody User user) {
        return service.add(user);
    }
}
