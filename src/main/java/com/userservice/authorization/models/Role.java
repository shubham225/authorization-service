package com.userservice.authorization.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@JsonSerialize(as = Role.class)
public class Role extends BaseModel{
    private String role;

    public Role() {
        role = "";
    }

    public Role(String role) {
        this.role = role;
    }
}
