package com.authenticator.user.controllers;

import com.authenticator.user.dtos.UserDto;
import com.authenticator.user.dtos.UserRequestDto;
import com.authenticator.user.models.User;
import com.authenticator.user.services.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/V1/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/{id}"
    )
    public UserDto getUsers(@PathVariable int id) throws Exception {
        User user = new User();
        user = userService.getUser(id);

        UserDto userDto = new UserDto();

        userDto.setUsername(user.getUsername());
        userDto.setIsActive(user.isActive());

        return userDto;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/signup"
    )
    public UserDto addUser(@RequestBody UserRequestDto user) {
        return userService.addUser(user);
    }
}
