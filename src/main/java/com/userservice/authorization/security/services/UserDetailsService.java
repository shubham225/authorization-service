package com.userservice.authorization.security.services;

import com.userservice.authorization.models.User;
import com.userservice.authorization.repositories.UserRepository;
import com.userservice.authorization.security.models.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private UserRepository userRepository;

    public UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if(userOptional.isEmpty())
            throw new UsernameNotFoundException("User doesn't exists");

        User user = userOptional.get();
        UserDetails userDetails = new UserDetails(user);

        return userDetails;
    }
}
