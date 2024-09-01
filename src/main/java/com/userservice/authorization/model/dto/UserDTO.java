package com.userservice.authorization.model.dto;

import com.userservice.authorization.model.entity.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class UserDTO {
    private UUID    id;
    private String  username;
    private String  password;
    private String  email;
    private String  mobile;
    private String  address;
    private String  city;
    private String  country;
    private boolean isActive;
    private boolean isAccountLocked;
    private List<Role> roles;
}
