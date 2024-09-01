package com.userservice.authorization.service;

import com.userservice.authorization.model.dto.ScopeDTO;
import com.userservice.authorization.model.entity.Scope;

import java.util.List;
import java.util.UUID;

public interface ScopeService {
    public List<ScopeDTO> getAllScopes();
    public ScopeDTO getScopeByID(UUID id);
    public Scope getScopeByName(String name);
    public ScopeDTO addNewScope(ScopeDTO user);
    public ScopeDTO modifyScope(ScopeDTO user);
}
