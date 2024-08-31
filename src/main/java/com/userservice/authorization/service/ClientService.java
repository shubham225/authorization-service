package com.userservice.authorization.service;

import com.userservice.authorization.model.dto.ClientDTO;
import com.userservice.authorization.model.dto.ClientRegistrationRequestDto;
import com.userservice.authorization.model.dto.ClientRegistrationResponseDto;
import com.userservice.authorization.model.entity.Client;

import java.util.List;

public interface ClientService {
    public ClientRegistrationResponseDto registerClient(ClientRegistrationRequestDto client) throws Exception;
    public ClientDTO getClientByName(String clientName);
    public List<ClientDTO> getAllClients();
    public ClientDTO getClientByID(String id);
    public ClientDTO addNewClient(ClientDTO dto);
    public ClientDTO regenerateSecret(String id);
}
