package com.userservice.authorization.model.mapper;

import com.userservice.authorization.model.dto.ScopeDTO;
import com.userservice.authorization.model.entity.Scope;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ScopeDTOMapper implements Function<Scope, ScopeDTO> {
    @Override
    public ScopeDTO apply(Scope scope) {
        return ScopeDTO.builder()
                .id(scope.getId())
                .scope(scope.getScope())
                .description(scope.getDescription())
                .build();
    }
}
