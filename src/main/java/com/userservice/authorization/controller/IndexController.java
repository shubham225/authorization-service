package com.userservice.authorization.controller;

import com.userservice.authorization.utils.AppDomain;
import com.userservice.authorization.utils.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
