package com.userservice.authorization.services;

import com.userservice.authorization.dtos.UserResponseDto;
import com.userservice.authorization.dtos.UserRequestDto;
import com.userservice.authorization.models.User;

public interface IUserService {
    public User getUser(int id) throws Exception;
    public UserResponseDto addUser(UserRequestDto user) throws Exception;
}
