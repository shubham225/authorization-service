package com.userservice.authorization.controller;

import com.userservice.authorization.model.dto.ClientDTO;
import com.userservice.authorization.model.dto.UserAddResponseDto;
import com.userservice.authorization.model.dto.UserAddRequestDto;
import com.userservice.authorization.model.dto.UserDTO;
import com.userservice.authorization.model.entity.Role;
import com.userservice.authorization.model.entity.User;
import com.userservice.authorization.model.result.AppResult;
import com.userservice.authorization.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/V1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(
            path = "/",
            method = RequestMethod.GET
    )
    public ResponseEntity<AppResult> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return AppResult.success("", users);
    }

    @RequestMapping(
            path = "/{id}",
            method = RequestMethod.GET
    )
    public ResponseEntity<AppResult> getUserByID(@PathVariable UUID id) {
        UserDTO user = userService.getUserByID(id);
        return AppResult.success("", user);
    }

    @RequestMapping(
            path = "/",
            method = RequestMethod.POST
    )
    public ResponseEntity<AppResult> addNewUser(@RequestBody UserDTO request) {
        UserDTO user = userService.addNewUser(request);
        return AppResult.success("", user);
    }
}
