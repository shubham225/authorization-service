package com.userservice.authorization.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClientRegistrationRequestDto {
    @JsonProperty( value = "client_name")
    private String clientName;
    @JsonProperty( value = "client_authentication_methods")
    private List<String> clientAuthenticationMethods;
    @JsonProperty( value = "authorization_grant_types")
    private List<String> authorizationGrantTypes;
    @JsonProperty( value = "redirect_uris")
    private List<String> redirectUris;
    @JsonProperty( value = "post_logout_redirect_uris")
    private List<String> postLogoutRedirectUris;
    @JsonProperty( value = "scopes")
    private List<String> scopes;
}
