package com.userservice.authorization.service.impl;

import com.userservice.authorization.exception.ClientNotFoundException;
import com.userservice.authorization.model.dto.ClientDTO;
import com.userservice.authorization.model.dto.ClientRegistrationRequestDto;
import com.userservice.authorization.exception.ClientAlreadyExistsException;
import com.userservice.authorization.model.dto.RegisterClientDTO;
import com.userservice.authorization.model.entity.Client;

import com.userservice.authorization.model.mapper.ClientDTOMapper;
import com.userservice.authorization.model.mapper.RegisterClientDTOMapper;
import com.userservice.authorization.repository.ClientRepository;
import com.userservice.authorization.service.ClientService;
import com.userservice.authorization.service.ScopeService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientService {
    private final RegisteredClientRepository registeredClientRepository;
    private final ClientRepository clientRepository;
    private final ClientDTOMapper clientDTOMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ScopeService scopeService;
    private final RegisterClientDTOMapper registerClientDTOMapper;

    public ClientServiceImpl(RegisteredClientRepository registeredClientRepository,
                             ClientRepository clientRepository,
                             BCryptPasswordEncoder bCryptPasswordEncoder, ClientDTOMapper clientDTOMapper, ScopeService scopeService, RegisterClientDTOMapper registerClientDTOMapper) {
        this.registeredClientRepository = registeredClientRepository;
        this.clientRepository = clientRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.clientDTOMapper = clientDTOMapper;
        this.scopeService = scopeService;
        this.registerClientDTOMapper = registerClientDTOMapper;
    }

    @Override
    public Client getClientByName(String clientName) {
        Optional<Client> clientOptional = clientRepository.findByClientName(clientName);

        if(clientOptional.isEmpty()) {
            throw new ClientNotFoundException("Client with name '" + clientName + "' not found.");
        }

        return clientOptional.get();
    }

    @Override
    public List<ClientDTO> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream()
                .map(clientDTOMapper).toList();
    }

    public Client getClientByID(String id) {
        Optional<Client> clientOptional = clientRepository.findByClientId(id);
        if (clientOptional.isEmpty())
            throw new ClientNotFoundException("Client with id '" + id + "' not found.");

        return clientOptional.get();
    }

    @Override
    public ClientDTO getClientDTOByID(String id) {
        return clientDTOMapper.apply(getClientByID(id));
    }

    @Override
    public RegisterClientDTO addNewClient(ClientDTO clientDTO) {
        String id = generateUniqueID();
        String secret = generateUniqueSecret();

        clientDTO.getScopes().forEach(
                scopeService::getScopeByName
        );

        RegisteredClient registeredClient = RegisteredClient.withId(id)
                .clientId(id)
                .clientName(clientDTO.getClientName())
                .clientSecret(bCryptPasswordEncoder.encode(secret))
                .clientIdIssuedAt(Instant.now())
                .clientAuthenticationMethods(clientAuthMethods -> clientDTO.getClientAuthenticationMethods().forEach(
                        clientAuthMethod -> clientAuthMethods.add(new ClientAuthenticationMethod(clientAuthMethod))
                ))
                .authorizationGrantTypes(authGrantTypes -> clientDTO.getAuthorizationGrantTypes().forEach(
                        authGrantType -> authGrantTypes.add(new AuthorizationGrantType(authGrantType))
                ))
                .redirectUris(redirectUris -> redirectUris
                        .addAll(clientDTO.getRedirectUris())
                )
                .postLogoutRedirectUris(postLogoutRedirectUris -> postLogoutRedirectUris
                        .addAll(clientDTO.getPostLogoutRedirectUris())
                )
                .scopes(scopes -> scopes.addAll(clientDTO.getScopes())
                )
                .build();

        registeredClientRepository.save(registeredClient);
        Client client = getClientByID(registeredClient.getClientId());
        client.setClientSecret(secret);

        return registerClientDTOMapper.apply(client);
    }

    @Override
    public RegisterClientDTO regenerateSecret(String id) {
        String secret = generateUniqueSecret();

        Client client = getClientByID(id);
        client.setClientSecret(bCryptPasswordEncoder.encode(secret));

        client = clientRepository.save(client);
        client.setClientSecret(secret);

        return registerClientDTOMapper.apply(client);
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
}
