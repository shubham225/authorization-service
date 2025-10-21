package com.userservice.authorization.service.impl;

import com.userservice.authorization.model.entity.Role;
import com.userservice.authorization.model.entity.User;
import com.userservice.authorization.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if(userOptional.isEmpty())
            throw new UsernameNotFoundException("User '"+ username + "' doesn't exists");

        User user = userOptional.get();

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(getGrantedAuthorities(user.getRoles()))
                .accountExpired(user.isAccountExpired())
                .accountLocked(user.isAccountLocked())
                .credentialsExpired(user.isCredentialsExpired())
                .disabled(!user.isActive())
                .build();

        // commented because of the 'Cannot invoke "com.userservice.authorization.models.User.getPassword()" because "this.user" is null' error with Authorization Code flow
        // TODO : Find out the cause of the error.
//        return new MyUserDetails(user);
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
