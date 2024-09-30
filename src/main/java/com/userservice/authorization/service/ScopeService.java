package com.userservice.authorization.service;

import com.userservice.authorization.model.dto.ScopeDTO;
import com.userservice.authorization.model.entity.Scope;

import java.util.List;
import java.util.UUID;

public interface ScopeService {
    List<ScopeDTO> getAllScopes();
    ScopeDTO getScopeByID(UUID id);
    Scope getScopeByName(String name);
    ScopeDTO addNewScope(ScopeDTO user);
    ScopeDTO modifyScope(ScopeDTO user);
}
