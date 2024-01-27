package com.userservice.authorization.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientRegistrationRequestDto {
    @JsonProperty( value = "client_name")
    private String clientName;
    @JsonProperty( value = "client_authentication_methods")
    private String[] clientAuthenticationMethods;
    @JsonProperty( value = "authorization_grant_types")
    private String[] authorizationGrantTypes;
    @JsonProperty( value = "redirect_uris")
    private String[] redirectUris;
    @JsonProperty( value = "post_logout_redirect_uris")
    private String[] postLogoutRedirectUris;
    @JsonProperty( value = "scopes")
    private String[] scopes;
}
