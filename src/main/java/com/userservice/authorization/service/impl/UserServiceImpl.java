package com.userservice.authorization.service.impl;

import com.userservice.authorization.model.dto.UserAddRequestDto;
import com.userservice.authorization.exception.IllegalValueException;
import com.userservice.authorization.exception.UserAlreadyExistsException;
import com.userservice.authorization.model.dto.UserDTO;
import com.userservice.authorization.model.entity.User;
import com.userservice.authorization.model.mapper.UserDTOMapper;
import com.userservice.authorization.repository.UserRepository;
import com.userservice.authorization.service.RoleService;
import com.userservice.authorization.service.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserDTOMapper userDTOMapper;
    private final RoleService  roleService;


    public UserServiceImpl(UserRepository userRepository,
                           UserDTOMapper userDTOMapper,
                           RoleService roleService) {
        this.userRepository = userRepository;
        this.userDTOMapper = userDTOMapper;
        this.roleService = roleService;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userDTOMapper).toList();
    }

    @Override
    public UserDTO getUserByID(UUID id) {
        Optional<User> userOptional = userRepository.findById(id);

        if(userOptional.isEmpty())
            throw new UsernameNotFoundException("User with id '"+ id + "' doesn't exists");

        return userDTOMapper.apply(userOptional.get());
    }

    @Override
    public UserDTO addNewUser(UserDTO user) {
        User user1 = User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .mobile(user.getMobile())
                .address(user.getAddress())
                .city(user.getCity())
                .isActive(true)
                .roles(
                        user.getRoles().stream().map(
                                role -> roleService.getRoleByName(role.getRole()))
                                .collect(Collectors.toSet())
                )
                .build();

        user1 = userRepository.save(user1);

        return userDTOMapper.apply(user1);
    }

    @Override
    public UserDTO modifyUserDetails(UserDTO user) {
        return null;
    }

    private void validateUserDetails(UserAddRequestDto user) throws Exception{
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
