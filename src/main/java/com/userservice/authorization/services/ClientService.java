package com.userservice.authorization.services;

import com.userservice.authorization.dtos.ClientRegistrationRequestDto;
import com.userservice.authorization.dtos.ClientRegistrationResponseDto;
import com.userservice.authorization.security.models.Client;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class ClientService implements IClientService {
    private RegisteredClientRepository clientRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public ClientService(RegisteredClientRepository clientRepository,
                         BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.clientRepository = clientRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public ClientRegistrationResponseDto registerClient(ClientRegistrationRequestDto client) {

        // Generate Unique Values
        String id = generateUniqueID();
        String secret = generateUniqueSecret();

        // Generate Registered Client Builder
        RegisteredClient.Builder clientBuilder = RegisteredClient.withId(id)
                .clientId(id)
                .clientSecret(bCryptPasswordEncoder.encode(secret));

        clientBuilder.clientName(client.getClientName());
        clientBuilder.clientIdIssuedAt(Instant.now());

        for(String clientAuthMethod : client.getClientAuthenticationMethods()) {
            clientBuilder.clientAuthenticationMethod(new ClientAuthenticationMethod(clientAuthMethod));
        }

        for(String authGrantType : client.getAuthorizationGrantTypes()) {
            clientBuilder.authorizationGrantType(new AuthorizationGrantType(authGrantType));
        }

        for(String scope : client.getScopes()) {
            clientBuilder.scope(scope);
        }

        for(String redirectUri : client.getRedirectUris()) {
            clientBuilder.redirectUri(redirectUri);
        }

        for(String postLogoutRedirectUri : client.getPostLogoutRedirectUris()) {
            clientBuilder.postLogoutRedirectUri(postLogoutRedirectUri);
        }

        // Build Client and Save
        RegisteredClient registeredClient = clientBuilder.build();
        clientRepository.save(registeredClient);

        return (new ClientRegistrationResponseDto(registeredClient.getClientId(), secret));
    }

    private String generateUniqueID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    private String generateUniqueSecret() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    @Override
    public Client getClientByName(String clientName) {
        return null;
    }
}
