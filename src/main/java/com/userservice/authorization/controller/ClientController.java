package com.userservice.authorization.controller;

import com.userservice.authorization.model.dto.ClientDTO;
import com.userservice.authorization.model.dto.RegisterClientDTO;
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
        ClientDTO client = clientService.getClientDTOByID(id);
        return AppResult.success("", client);
    }

    @RequestMapping(
            path = "/register",
            method = RequestMethod.POST
    )
    public ResponseEntity<AppResult> addNewClient(@RequestBody ClientDTO request) {
        RegisterClientDTO client = clientService.addNewClient(request);
        return AppResult.success("", client);
    }

    @RequestMapping(
            path = "/{id}",
            method = RequestMethod.PUT
    )
    public ResponseEntity<AppResult> regenerateSecret(@PathVariable String id) {
        RegisterClientDTO client = clientService.regenerateSecret(id);
        return AppResult.success("", client);
    }
}