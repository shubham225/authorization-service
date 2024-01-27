package com.userservice.authorization.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ClientRegistrationResponseDto {
    @JsonProperty( value = "client_id")
    private String clientID;
    @JsonProperty( value = "client_secret")
    private String clientSecret;
}
