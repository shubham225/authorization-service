package com.userservice.authorization.security.services;

import com.userservice.authorization.models.Role;
import com.userservice.authorization.models.User;
import com.userservice.authorization.repositories.UserRepository;
import com.userservice.authorization.security.models.MyGrantedAuthority;
import com.userservice.authorization.security.models.MyUserDetails;
import jakarta.transaction.Transactional;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

//@Service
@Service("userDetailsService")
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

//        return new org.springframework.security.core.userdetails.User(
//                user.getUsername(),
//                user.getPassword(),
//                user.isActive(),
//                user.isAccountNonExpired(),
//                user.isCredentialsNonExpired(),
//                user.isAccountNonLocked(),
//                getGrantedAuthorities(user.getRoles())
//        );

        return new MyUserDetails(user);
    }

//    private Collection<? extends GrantedAuthority> getGrantedAuthorities(Set<Role> roles) {
//        List<MyGrantedAuthority> list = new ArrayList<>();
//
//        for(Role role : roles)
//            list.add(new MyGrantedAuthority(role));
//
//        return list;
//    }
}
