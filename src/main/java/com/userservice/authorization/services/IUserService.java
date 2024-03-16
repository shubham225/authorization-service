package com.userservice.authorization.services;

import com.userservice.authorization.dtos.UserAddResponseDto;
import com.userservice.authorization.dtos.UserAddRequestDto;
import com.userservice.authorization.models.User;

import java.util.List;

public interface IUserService {
    public User getUser(int id) throws Exception;
    public List<User> getAllUser();
    public UserAddResponseDto addUser(UserAddRequestDto user) throws Exception;
}
