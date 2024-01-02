package com.authenticator.user.services;

import com.authenticator.user.dtos.ExceptionDto;
import com.authenticator.user.dtos.UserDto;
import com.authenticator.user.exceptions.NotFoundException;
import com.authenticator.user.models.User;
import com.authenticator.user.repositories.UserRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService implements IUserService{
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User getUser(int id) throws Exception {
        Optional<User> userOptional = userRepository.findById(id);

        if(userOptional.isEmpty())
            throw new NotFoundException("User " + id + " do not exists");

        return userOptional.get();
    }
}
