package com.authenticator.user.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User extends BaseModel {
    private String  username;
    private String  password;
    private boolean isActive;
}
