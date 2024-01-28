package com.userservice.authorization.services;

import com.userservice.authorization.dtos.UserResponseDto;
import com.userservice.authorization.dtos.UserRequestDto;
import com.userservice.authorization.exceptions.IllegalValueException;
import com.userservice.authorization.exceptions.NullUserRolesException;
import com.userservice.authorization.exceptions.UserAlreadyExistsException;
import com.userservice.authorization.models.Role;
import com.userservice.authorization.models.User;
import com.userservice.authorization.repositories.RoleRepository;
import com.userservice.authorization.repositories.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

@Service
public class UserService implements IUserService{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @Override
    public User getUser(int id) throws Exception {
        Optional<User> userOptional = userRepository.findById(id);

        if(userOptional.isEmpty())
            throw new UsernameNotFoundException("User with id '"+ id + "' doesn't exists");

        return userOptional.get();
    }

    public UserResponseDto addUser(UserRequestDto user) throws Exception {

        validateUserDetails(user);

        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        newUser.setActive(false);

        if(user.getRoles() != null) {
            for(String role : user.getRoles()) {
                Optional<Role> optionalRole = roleRepository.findByRole(role);

                if(optionalRole.isEmpty())
                    newUser.getRoles().add(new Role(role));
                else
                    newUser.getRoles().add(optionalRole.get());
            }
        }else {
            throw new NullUserRolesException("User roles should not be null");
        }

        userRepository.save(newUser);

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setUsername(newUser.getUsername());
        userResponseDto.setIsActive(false);
        userResponseDto.setRoles(user.getRoles());

        return userResponseDto;
    }

    private void validateUserDetails(UserRequestDto user) throws Exception{
        Assert.notNull(user, "request should have value.");
        Assert.notNull(user.getUsername(), "username should be present in the request");
        Assert.notNull(user.getPassword(), "password should be present in the request");
        Assert.hasText(user.getUsername(), "username cannot be blank.");
        Assert.hasText(user.getPassword(), "password cannot be blank.");

        if (user.getPassword().length() < 8) {
            throw new IllegalValueException("password must be at least 8 characters long.");
        }

        Optional<User> userOptional = userRepository.findByUsername(user.getUsername());

        if(userOptional.isPresent()) {
            throw new UserAlreadyExistsException("username '" + user.getUsername() + "' already exists, user different username.");
        }

    }
}
