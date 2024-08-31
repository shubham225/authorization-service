package com.userservice.authorization.model.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@JsonSerialize(as = Role.class)
public class Role extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String role;

    public Role() {
        role = "";
    }

    public Role(String role) {
        this.role = role;
    }
}
