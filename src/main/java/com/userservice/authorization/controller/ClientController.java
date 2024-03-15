package com.userservice.authorization.controller;

import com.userservice.authorization.dto.ClientRegistrationRequestDto;
import com.userservice.authorization.dto.ClientRegistrationResponseDto;
import com.userservice.authorization.service.IClientService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/V1/client")
public class ClientController {
    private final IClientService clientService;

    public ClientController(IClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping(
            path = "/register",
            method = RequestMethod.POST
    )
    public ClientRegistrationResponseDto register(@RequestBody ClientRegistrationRequestDto client) throws Exception {
            return clientService.registerClient(client);
    }
}
