package com.authenticator.user.security.services;

import com.authenticator.user.models.User;
import com.authenticator.user.repositories.UserRepository;
import com.authenticator.user.security.models.UserDetails;
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
