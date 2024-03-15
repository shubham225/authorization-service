package com.userservice.authorization.service;

import com.userservice.authorization.dto.UserAddResponseDto;
import com.userservice.authorization.dto.UserAddRequestDto;
import com.userservice.authorization.model.User;

import java.util.List;

public interface IUserService {
    public User getUser(int id) throws Exception;
    public List<User> getAllUser();
    public UserAddResponseDto addUser(UserAddRequestDto user) throws Exception;
}
