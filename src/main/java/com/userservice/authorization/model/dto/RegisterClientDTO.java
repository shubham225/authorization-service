package com.userservice.authorization.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class RegisterClientDTO {
    private String id;
    @NotBlank
    private String clientId;
    @NotBlank
    private String clientSecret;
    @NotBlank
    private String clientName;
    @NotEmpty
    private Set<String> clientAuthenticationMethods;
    @NotEmpty
    private Set<String> authorizationGrantTypes;
    @NotEmpty
    private Set<String> redirectUris;
    @NotEmpty
    private Set<String> postLogoutRedirectUris;
    @NotEmpty
    private Set<String> scopes;
}
