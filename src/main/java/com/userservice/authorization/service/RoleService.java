package com.userservice.authorization.service;

import com.userservice.authorization.exception.RoleNotFoundException;
import com.userservice.authorization.model.dto.RoleDTO;
import com.userservice.authorization.model.entity.Role;

import java.util.List;
import java.util.UUID;

public interface RoleService {
    public List<RoleDTO> getAllRoles();
    public RoleDTO getRoleByID(UUID id);
    public RoleDTO addNewRole(RoleDTO user);
    public RoleDTO modifyRole(RoleDTO user);
    public Role getRoleByName(String name);
}
