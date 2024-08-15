package com.userservice.authorization.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    String getIndex() {
        return "index";
    }

    @GetMapping("/login")
    String getLogin() {
        return "login";
    }
}
