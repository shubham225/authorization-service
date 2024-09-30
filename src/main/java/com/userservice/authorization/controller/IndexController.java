package com.userservice.authorization.controller;

import com.userservice.authorization.utils.AppDomain;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    String getIndex(final Model model) {
        String domain = AppDomain.getInstance().getHttpPath();
        model.addAttribute("domain", domain);
        return "index";
    }

    @GetMapping("/login")
    String getLogin() {
        return "login";
    }
}
