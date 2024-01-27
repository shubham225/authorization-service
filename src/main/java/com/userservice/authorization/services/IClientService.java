package com.userservice.authorization.services;

import com.userservice.authorization.dtos.ClientRegistrationRequestDto;
import com.userservice.authorization.dtos.ClientRegistrationResponseDto;
import com.userservice.authorization.security.models.Client;

public interface IClientService {
    public ClientRegistrationResponseDto registerClient(ClientRegistrationRequestDto client);
    public Client getClientByName(String clientName);
}
