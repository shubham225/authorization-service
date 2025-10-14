package com.userservice.authorization.service.impl;

import com.userservice.authorization.exception.RoleNotFoundException;
import com.userservice.authorization.model.dto.RoleDTO;
import com.userservice.authorization.model.entity.Role;
import com.userservice.authorization.repository.RoleRepository;
import com.userservice.authorization.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.userservice.authorization.model.mapper.RoleDTOMapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleDTOMapper roleDTOMapper;

    @Override
    public List<RoleDTO> getAllRoles() {
        List<Role> roles = roleRepository.findAll();

        return roles.stream().map(roleDTOMapper).toList();
    }

    @Override
    public RoleDTO getRoleByID(UUID id) {
        Optional<Role> roleOptional = roleRepository.findById(id);

        if(roleOptional.isEmpty())
            throw new RoleNotFoundException("Role with id '" + id + "' doesn't exist.");

        return roleDTOMapper.apply(roleOptional.get());
    }

    @Override
    public RoleDTO addNewRole(RoleDTO role) {
        Role newRole = Role.builder()
                .role(role.getRole())
                .description(role.getDescription())
                .build();

        newRole = roleRepository.save(newRole);

        return roleDTOMapper.apply(newRole);
    }

    @Override
    public RoleDTO modifyRole(RoleDTO user) {
        return null;
    }

    @Override
    public Role getRoleByName(String name) {
        Optional<Role> roleOptional = roleRepository.findByRole(name);

        if (roleOptional.isEmpty())
            throw new RoleNotFoundException("Role with name '" + name + "' doesn't exist.");

        return roleOptional.get();
    }
}
