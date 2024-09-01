package com.userservice.authorization.model.mapper;

import com.userservice.authorization.model.dto.ClientDTO;
import com.userservice.authorization.model.entity.Client;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import java.util.function.Function;

@Component
public class ClientDTOMapper implements Function<Client, ClientDTO> {
    @Override
    public ClientDTO apply(Client client) {
        return ClientDTO.builder()
                .id(client.getId())
                .clientId(client.getClientId())
                .clientName(client.getClientName())
                .clientAuthenticationMethods(StringUtils.commaDelimitedListToSet(client.getClientAuthenticationMethods()))
                .authorizationGrantTypes(StringUtils.commaDelimitedListToSet(client.getAuthorizationGrantTypes()))
                .redirectUris(StringUtils.commaDelimitedListToSet(client.getRedirectUris()))
                .postLogoutRedirectUris(StringUtils.commaDelimitedListToSet(client.getPostLogoutRedirectUris()))
                .scopes(StringUtils.commaDelimitedListToSet(client.getScopes()))
                .build();
    }
}
