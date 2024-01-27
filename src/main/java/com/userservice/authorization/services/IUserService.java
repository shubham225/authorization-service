package com.userservice.authorization.services;

import com.userservice.authorization.dtos.UserDto;
import com.userservice.authorization.dtos.UserRequestDto;
import com.userservice.authorization.models.User;

public interface IUserService {
    public User getUser(int id) throws Exception;
    public UserDto addUser(UserRequestDto user);
}
