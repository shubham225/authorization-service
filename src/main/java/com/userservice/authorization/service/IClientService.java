package com.userservice.authorization.service;

import com.userservice.authorization.dto.ClientRegistrationRequestDto;
import com.userservice.authorization.dto.ClientRegistrationResponseDto;
import com.userservice.authorization.security.models.Client;

public interface IClientService {
    public ClientRegistrationResponseDto registerClient(ClientRegistrationRequestDto client) throws Exception;
    public Client getClientByName(String clientName);
}
