package com.userservice.authorization.controller;

import com.userservice.authorization.exception.RoleNotFoundException;
import com.userservice.authorization.model.dto.ClientDTO;
import com.userservice.authorization.model.dto.RoleDTO;
import com.userservice.authorization.model.result.AppResult;
import com.userservice.authorization.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/V1/role")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @RequestMapping(
            path = "/",
            method = RequestMethod.GET
    )
    public ResponseEntity<AppResult> getAllRoles() {
        List<RoleDTO> roles = roleService.getAllRoles();
        return AppResult.success("", roles);
    }

    @RequestMapping(
            path = "/{id}",
            method = RequestMethod.GET
    )
    public ResponseEntity<AppResult> getRoleByID(@PathVariable UUID id) {
        RoleDTO role = roleService.getRoleByID(id);
        return AppResult.success("", role);
    }

    @RequestMapping(
            path = "/",
            method = RequestMethod.POST
    )
    public ResponseEntity<AppResult> addNewRole(@RequestBody RoleDTO request) {
        RoleDTO role = roleService.addNewRole(request);
        return AppResult.success("", role);
    }
}
