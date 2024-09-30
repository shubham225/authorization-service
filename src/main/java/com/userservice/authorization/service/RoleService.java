package com.userservice.authorization.service;

import com.userservice.authorization.model.dto.RoleDTO;
import com.userservice.authorization.model.entity.Role;

import java.util.List;
import java.util.UUID;

public interface RoleService {
    List<RoleDTO> getAllRoles();
    RoleDTO getRoleByID(UUID id);
    RoleDTO addNewRole(RoleDTO user);
    RoleDTO modifyRole(RoleDTO user);
    Role getRoleByName(String name);
}
