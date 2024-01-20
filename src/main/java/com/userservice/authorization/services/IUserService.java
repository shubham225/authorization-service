package com.userservice.authorization.services;

import com.userservice.authorization.models.User;

public interface IUserService {
    public User getUser(int id) throws Exception;
}
