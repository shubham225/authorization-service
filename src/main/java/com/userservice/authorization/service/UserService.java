package com.userservice.authorization.service;

import com.userservice.authorization.model.dto.UserDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {
    public List<UserDTO> getAllUsers();
    public UserDTO getUserByID(UUID id);
    public UserDTO addNewUser(UserDTO user);
    public UserDTO modifyUserDetails(UserDTO user);
}
