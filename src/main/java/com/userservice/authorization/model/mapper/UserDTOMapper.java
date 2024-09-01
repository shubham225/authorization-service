package com.userservice.authorization.model.mapper;

import com.userservice.authorization.model.dto.UserDTO;
import com.userservice.authorization.model.entity.User;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserDTOMapper implements Function<User, UserDTO> {

    @Override
    public UserDTO apply(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .isAccountLocked(user.isAccountLocked())
                .isActive(user.isActive())
                .build();
    }
}