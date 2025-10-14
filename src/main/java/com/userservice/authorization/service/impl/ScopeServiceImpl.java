package com.userservice.authorization.service.impl;

import com.userservice.authorization.exception.ScopeNotFoundException;
import com.userservice.authorization.model.dto.ScopeDTO;
import com.userservice.authorization.model.entity.Scope;
import com.userservice.authorization.model.mapper.ScopeDTOMapper;
import com.userservice.authorization.repository.ScopeRepository;
import com.userservice.authorization.service.ScopeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ScopeServiceImpl implements ScopeService {
    private final ScopeRepository scopeRepository;
    private final ScopeDTOMapper scopeDTOMapper;

    @Override
    public List<ScopeDTO> getAllScopes() {
        List<Scope> scopes = scopeRepository.findAll();

        return scopes.stream().map(scopeDTOMapper).toList();
    }

    @Override
    public ScopeDTO getScopeByID(UUID id) {
        Optional<Scope> scopeOptional = scopeRepository.findById(id);

        if(scopeOptional.isEmpty())
            throw new ScopeNotFoundException("Scope with id '" + id + "' doesn't exists");

        return scopeDTOMapper.apply(scopeOptional.get());
    }

    @Override
    public Scope getScopeByName(String name) {
        Optional<Scope> scopeOptional = scopeRepository.findByScope(name);

        if(scopeOptional.isEmpty())
            throw new ScopeNotFoundException("Scope with name '" + name + "' doesn't exists");

        return scopeOptional.get();
    }

    @Override
    public ScopeDTO addNewScope(ScopeDTO scope) {
        Scope newScope = Scope.builder()
                .scope(scope.getScope())
                .description(scope.getDescription())
                .build();

        newScope = scopeRepository.save(newScope);

        return scopeDTOMapper.apply(newScope);
    }

    @Override
    public ScopeDTO modifyScope(ScopeDTO user) {
        return null;
    }
}
