package com.authenticator.user.services;

import com.authenticator.user.models.User;

public interface IUserService {
    public User getUser(int id) throws Exception;
}
