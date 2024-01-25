package com.userservice.authorization.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@JsonSerialize(as = User.class)
public class User extends BaseModel {
    private String  username;
    private String  password;
    private boolean isActive;
    private boolean isCredentialsExpired;
    private boolean isAccountLocked;
    private boolean isAccountExpired;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    //Add extra user info if needed

    public User() {
        this.username = "";
        this.password = "";
        this.isActive = false;
        this.isAccountExpired = false;
        this.isAccountLocked = false;
        this.isCredentialsExpired = false;
        this.roles = new HashSet<>();
    }
}
