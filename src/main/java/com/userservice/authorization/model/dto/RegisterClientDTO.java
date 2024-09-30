package com.userservice.authorization.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class RegisterClientDTO {
    private String id;
    private String clientId;
    private String clientSecret;
    private String clientName;
    private Set<String> clientAuthenticationMethods;
    private Set<String> authorizationGrantTypes;
    private Set<String> redirectUris;
    private Set<String> postLogoutRedirectUris;
    private Set<String> scopes;
}
