package com.userservice.authorization.service;

import com.userservice.authorization.model.dto.ClientDTO;
import com.userservice.authorization.model.dto.RegisterClientDTO;
import com.userservice.authorization.model.entity.Client;

import java.util.List;

public interface ClientService {
    Client getClientByName(String clientName);
    Client getClientByID(String id);
    List<ClientDTO> getAllClients();
    ClientDTO getClientDTOByID(String id);
    RegisterClientDTO addNewClient(ClientDTO dto);
    RegisterClientDTO regenerateSecret(String id);
    Long getTotalCount();
}
