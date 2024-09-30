package com.userservice.authorization.repository;

import com.userservice.authorization.model.entity.Scope;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ScopeRepository extends JpaRepository<Scope, UUID> {
    Optional<Scope> findByScope(String name);
}
