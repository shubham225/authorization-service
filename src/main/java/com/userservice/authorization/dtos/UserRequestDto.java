package com.userservice.authorization.dtos;

import com.userservice.authorization.models.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserRequestDto {
    private String  username;
    private String  password;
//    private Set<String> roles;
}
