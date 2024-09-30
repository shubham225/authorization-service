package com.userservice.authorization.model.mapper;

import com.userservice.authorization.model.dto.UserCreationDTO;
import com.userservice.authorization.model.entity.Role;
import com.userservice.authorization.model.entity.User;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class UserResponseDTOMapper implements Function<User, UserCreationDTO> {

    @Override
    public UserCreationDTO apply(User user) {
        return UserCreationDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .mobile(user.getMobile())
                .address(user.getAddress())
                .city(user.getCity())
                .country(user.getCountry())
                .roles(user.getRoles().stream().map(Role::getRole).collect(Collectors.toSet()))
                .isAccountLocked(user.isAccountLocked())
                .isActive(user.isActive())
                .build();
    }
}
