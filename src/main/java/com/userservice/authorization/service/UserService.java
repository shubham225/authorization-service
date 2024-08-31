package com.userservice.authorization.service;

import com.userservice.authorization.model.dto.UserAddResponseDto;
import com.userservice.authorization.model.dto.UserAddRequestDto;
import com.userservice.authorization.model.entity.User;

import java.util.List;

public interface UserService {
    public User getUser(int id) throws Exception;
    public List<User> getAllUser();
    public UserAddResponseDto addUser(UserAddRequestDto user) throws Exception;
}
