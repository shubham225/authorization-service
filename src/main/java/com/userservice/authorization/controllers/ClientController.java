package com.userservice.authorization.controllers;

import com.userservice.authorization.dtos.ClientRegistrationRequestDto;
import com.userservice.authorization.dtos.ClientRegistrationResponseDto;
import com.userservice.authorization.services.IClientService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/V1/clients")
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
