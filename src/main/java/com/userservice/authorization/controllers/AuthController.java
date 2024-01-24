package com.userservice.authorization.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/V1/auth")
public class AuthController {

    @RequestMapping(
            path = "/signup",
            method = RequestMethod.GET
    )
    public String login() {
        return "Login Form";
    }
}
