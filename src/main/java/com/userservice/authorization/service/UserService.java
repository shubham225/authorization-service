package com.userservice.authorization.service;

import com.userservice.authorization.model.dto.ChangePasswordDTO;
import com.userservice.authorization.model.dto.CustomMetricsDTO;
import com.userservice.authorization.model.dto.UserCreationDTO;
import com.userservice.authorization.model.dto.UserDTO;
import com.userservice.authorization.model.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<UserDTO> getAllUsers();
    UserDTO getUserDTOByID(UUID id);
    UserDTO addNewUser(UserCreationDTO user);
    UserDTO changePassword(String Username, ChangePasswordDTO user);
    User getUserByID(UUID id);
    UserDTO getUserByUsername(String Username);
    Long getTotalCount();
}
