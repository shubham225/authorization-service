package com.userservice.authorization.controllers;

import com.userservice.authorization.utils.AppDomain;
import com.userservice.authorization.utils.IpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    final private Logger logger = LoggerFactory.getLogger(ClientController.class);

    @GetMapping("/")
    String getIndex(final Model model) {
        String port = IpUtils.getPort();
        String host = IpUtils.getHost();
        String domain = "http://" + String.join(":", host, String.valueOf(port));
        model.addAttribute("domain", domain);
        return "index";
    }

    @GetMapping("/login")
    String getLogin() {
        return "login";
    }
}
