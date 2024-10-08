package com.userservice.authorization.controller;

import com.userservice.authorization.common.message.AuthMessage;
import com.userservice.authorization.model.dto.ChangePasswordDTO;
import com.userservice.authorization.model.dto.CustomMetricsDTO;
import com.userservice.authorization.model.dto.UserCreationDTO;
import com.userservice.authorization.model.dto.UserDTO;
import com.userservice.authorization.model.result.AppResult;
import com.userservice.authorization.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/V1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(
            path = "",
            method = RequestMethod.GET
    )
    public ResponseEntity<AppResult> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return AppResult.success(AuthMessage.SUCCESS_MESSAGE, users);
    }

    @RequestMapping(
            path = "/{id}",
            method = RequestMethod.GET
    )
    public ResponseEntity<AppResult> getUserByID(@PathVariable UUID id) {
        UserDTO user = userService.getUserDTOByID(id);
        return AppResult.success(AuthMessage.SUCCESS_MESSAGE, user);
    }

    @RequestMapping(
            path = "",
            method = RequestMethod.POST
    )
    public ResponseEntity<AppResult> addNewUser(@RequestBody UserCreationDTO request) {
        UserDTO user = userService.addNewUser(request);
        return AppResult.success(AuthMessage.SUCCESS_MESSAGE, user);
    }

    @RequestMapping(
            path = "/changePassword",
            method = RequestMethod.POST
    )
    public ResponseEntity<AppResult> changePassword(@RequestBody ChangePasswordDTO request, Principal principal) {
        String UserName = principal.getName();
        UserDTO user = userService.changePassword(UserName, request);
        return AppResult.success(AuthMessage.PASSWORD_CHANGED_MESSAGE, user);
    }

    @RequestMapping(
            path = "/count",
            method = RequestMethod.GET
    )
    public ResponseEntity<AppResult> getCount() {
        Long userCount = userService.getTotalCount();
        return AppResult.success(AuthMessage.SUCCESS_MESSAGE, userCount);
    }

    @RequestMapping(
            path = "/login",
            method = RequestMethod.GET
    )
    public ResponseEntity<AppResult> getUserByLogin(Principal principal) {
        String UserName = principal.getName();
        UserDTO user = userService.getUserByUsername(UserName);
        return AppResult.success(AuthMessage.SUCCESS_MESSAGE, user);
    }
}
