package com.userservice.authorization.service.impl;

import com.userservice.authorization.exception.InvalidPasswordException;
import com.userservice.authorization.model.dto.ChangePasswordDTO;
import com.userservice.authorization.model.dto.CustomMetricsDTO;
import com.userservice.authorization.model.dto.UserCreationDTO;
import com.userservice.authorization.model.dto.UserDTO;
import com.userservice.authorization.model.entity.User;
import com.userservice.authorization.model.mapper.UserDTOMapper;
import com.userservice.authorization.repository.UserRepository;
import com.userservice.authorization.service.ClientService;
import com.userservice.authorization.service.RoleService;
import com.userservice.authorization.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserDTOMapper userDTOMapper;
    private final RoleService  roleService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userDTOMapper).toList();
    }

    @Override
    public UserDTO getUserDTOByID(UUID id) {
        User user = getUserByID(id);

        return userDTOMapper.apply(user);
    }

    @Override
    public User getUserByID(UUID id) {
        Optional<User> userOptional = userRepository.findById(id);

        if(userOptional.isEmpty())
            throw new UsernameNotFoundException("User with id '"+ id + "' doesn't exists");

        return userOptional.get();
    }

    @Override
    public UserDTO getUserByUsername(String Username) {
        User user = getUserByName(Username);
        return userDTOMapper.apply(user);
    }

    @Override
    public Long getTotalCount() {
        return userRepository.count();
    }

    @Override
    public UserDTO addNewUser(UserCreationDTO user) {
        user.getRoles().forEach(
                roleService::getRoleByName
        );

        User user1 = User.builder()
                .username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .email(user.getEmail())
                .mobile(user.getMobile())
                .address(user.getAddress())
                .city(user.getCity())
                .isActive(true)
                .roles( user.getRoles().stream()
                        .map(roleService::getRoleByName)
                        .collect(Collectors.toSet())
                )
                .build();

        user1 = userRepository.save(user1);

        return userDTOMapper.apply(user1);
    }

    public User getUserByName(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if(userOptional.isEmpty())
            throw new UsernameNotFoundException("User with username '"+ username + "' doesn't exists");

        return userOptional.get();
    }

    @Override
    public UserDTO changePassword(String username, ChangePasswordDTO userDto) {
        User user = getUserByName(username);
        validatePassword(userDto, user);
        user.setPassword(passwordEncoder.encode(userDto.getNewPassword()));

        user = userRepository.save(user);
        return userDTOMapper.apply(user);
    }

    private void validatePassword(ChangePasswordDTO userDTO, User user) {
        Assert.notNull(userDTO, "request should have some value.");
//        Assert.notNull(userDTO.getUserId(), "User ID should be present in the request");
        Assert.notNull(userDTO.getNewPassword(), "password should be present in the request");
        Assert.hasText(userDTO.getNewPassword(), "password cannot be blank.");

        if(!passwordEncoder.matches(userDTO.getOldPassword(), user.getPassword())) {
            throw new InvalidPasswordException("Old Password is wrong");
        }

        if (userDTO.getNewPassword().length() < 8) {
            throw new RuntimeException("password must be at least 8 characters long.");
        }
    }
}
