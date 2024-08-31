package com.userservice.authorization.controller;

import com.userservice.authorization.model.dto.ClientDTO;
import com.userservice.authorization.model.dto.ClientRegistrationRequestDto;
import com.userservice.authorization.model.dto.ClientRegistrationResponseDto;
import com.userservice.authorization.model.entity.Client;
import com.userservice.authorization.model.result.AppResult;
import com.userservice.authorization.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/V1/client")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping(
            path = "/register",
            method = RequestMethod.POST
    )
    public ClientRegistrationResponseDto register(@RequestBody ClientRegistrationRequestDto client) throws Exception {
        return clientService.registerClient(client);
    }

    @RequestMapping(
            path = "/",
            method = RequestMethod.GET
    )
    public ResponseEntity<AppResult> getAllClients() {
        List<ClientDTO> client = clientService.getAllClients();
        return AppResult.success("", client);
    }

    @RequestMapping(
            path = "/{id}",
            method = RequestMethod.GET
    )
    public ResponseEntity<AppResult> getClientByID(@PathVariable String id) {
        ClientDTO client = clientService.getClientByID(id);
        return AppResult.success("", client);
    }

    @RequestMapping(
            path = "/",
            method = RequestMethod.POST
    )
    public ResponseEntity<AppResult> addNewClient(@RequestBody ClientDTO request) {
        ClientDTO client = clientService.addNewClient(request);
        return AppResult.success("", client);
    }

    @RequestMapping(
            path = "/{id}",
            method = RequestMethod.PUT
    )
    public ResponseEntity<AppResult> regenerateSecret(@PathVariable String id) {
        ClientDTO client = clientService.regenerateSecret(id);
        return AppResult.success("", client);
    }
}