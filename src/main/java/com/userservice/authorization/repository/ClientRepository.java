package com.userservice.authorization.repository;

import java.util.Optional;

import com.userservice.authorization.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
    Optional<Client> findByClientId(String clientId);
    Optional<Client> findByClientName(String clientName);
}