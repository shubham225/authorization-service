package com.userservice.authorization.service;

import com.userservice.authorization.model.dto.ClientDTO;
import com.userservice.authorization.model.dto.RegisterClientDTO;
import com.userservice.authorization.model.entity.Client;

import java.util.List;

public interface ClientService {
    public Client getClientByName(String clientName);
    public Client getClientByID(String id);
    public List<ClientDTO> getAllClients();
    public ClientDTO getClientDTOByID(String id);
    public RegisterClientDTO addNewClient(ClientDTO dto);
    public RegisterClientDTO regenerateSecret(String id);
}
