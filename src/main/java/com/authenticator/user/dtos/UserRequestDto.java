package com.authenticator.user.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {
    private String  username;
    private String  password;
    private boolean isActive;
}
