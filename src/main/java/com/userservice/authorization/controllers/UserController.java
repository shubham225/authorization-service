package com.userservice.authorization.controllers;

import com.userservice.authorization.dtos.UserResponseDto;
import com.userservice.authorization.dtos.UserRequestDto;
import com.userservice.authorization.models.Role;
import com.userservice.authorization.models.User;
import com.userservice.authorization.services.IUserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/V1/users")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/{id}"
    )
    public UserResponseDto getUsers(@PathVariable int id) throws Exception {
        User user = new User();
        user = userService.getUser(id);

        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setUsername(user.getUsername());
        userResponseDto.setIsActive(user.isActive());
//        for(Role role : user.getRoles()) {
//            userResponseDto.getRoles().add(role.getRole());
//        }

        return userResponseDto;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/signup"
    )
    public UserResponseDto addUser(@RequestBody UserRequestDto user) throws Exception {
        return userService.addUser(user);
    }
}
