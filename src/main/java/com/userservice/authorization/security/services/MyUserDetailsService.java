package com.userservice.authorization.security.services;

import com.userservice.authorization.models.Role;
import com.userservice.authorization.models.User;
import com.userservice.authorization.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if(userOptional.isEmpty())
            throw new UsernameNotFoundException("User doesn't exists");

        User user = userOptional.get();

        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(getGrantedAuthorities(user.getRoles()))
                .accountExpired(user.isAccountExpired())
                .accountLocked(user.isAccountLocked())
                .credentialsExpired(user.isCredentialsExpired())
                .disabled(user.isActive())
                .build();

        return userDetails;
    }

    private String[] getGrantedAuthorities(Set<Role> roles) {
        String[] roleArray = new String[roles.size()];
        int i = 0;

        for(Role role : roles) {
            roleArray[i] = role.getRole();
            i++;
        }

        return roleArray;
    }
}
