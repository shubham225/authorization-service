package com.userservice.authorization.services;

import com.userservice.authorization.dtos.ClientRegistrationRequestDto;
import com.userservice.authorization.dtos.ClientRegistrationResponseDto;
import com.userservice.authorization.exceptions.ClientAlreadyExistsException;
import com.userservice.authorization.exceptions.IllegalValueException;
import com.userservice.authorization.security.models.Client;

import com.userservice.authorization.security.repositories.ClientRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService implements IClientService {
    private final RegisteredClientRepository registeredClientRepository;
    private final ClientRepository clientRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public ClientService(RegisteredClientRepository registeredClientRepository,
                         ClientRepository clientRepository,
                         BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.registeredClientRepository = registeredClientRepository;
        this.clientRepository = clientRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public ClientRegistrationResponseDto registerClient(ClientRegistrationRequestDto client) throws Exception {
        // Run Validations
        validateClientDetails(client);

        // Generate Unique Values
        String id = generateUniqueID();
        String secret = generateUniqueSecret();

        // Generate Registered Client Builder
        RegisteredClient.Builder clientBuilder = RegisteredClient.withId(id)
                .clientId(id)
                .clientSecret(bCryptPasswordEncoder.encode(secret));

        clientBuilder.clientName(client.getClientName());
        clientBuilder.clientIdIssuedAt(Instant.now());

        if(client.getClientAuthenticationMethods() != null) {
            for (String clientAuthMethod : client.getClientAuthenticationMethods()) {
                clientBuilder.clientAuthenticationMethod(new ClientAuthenticationMethod(clientAuthMethod));
            }
        }

        if(client.getAuthorizationGrantTypes() != null) {
            for (String authGrantType : client.getAuthorizationGrantTypes()) {
                clientBuilder.authorizationGrantType(new AuthorizationGrantType(authGrantType));
            }
        }

        if(client.getScopes() != null) {
            for (String scope : client.getScopes()) {
                clientBuilder.scope(scope);
            }
        }

        if(client.getRedirectUris() != null) {
            for (String redirectUri : client.getRedirectUris()) {
                clientBuilder.redirectUri(redirectUri);
            }
        }

        if(client.getPostLogoutRedirectUris() != null) {
            for (String postLogoutRedirectUri : client.getPostLogoutRedirectUris()) {
                clientBuilder.postLogoutRedirectUri(postLogoutRedirectUri);
            }
        }

        // Build Client and Save
        RegisteredClient registeredClient = clientBuilder.build();
        registeredClientRepository.save(registeredClient);

        return (new ClientRegistrationResponseDto(registeredClient.getClientId(), secret));
    }

    private void validateClientDetails(ClientRegistrationRequestDto client) throws Exception {
        Assert.notNull(client, "request should have value.");
        Assert.notNull(client.getClientName(), "client_name should be present in the request.");
        Assert.hasText(client.getClientName(), "client_name cannot be blank.");

        Optional<Client> clientOptional = clientRepository.findByClientName(client.getClientName());

        if(clientOptional.isPresent())
            throw new ClientAlreadyExistsException("client_name '" + client.getClientName() +
                                                   "' already in use, use different client name");
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
