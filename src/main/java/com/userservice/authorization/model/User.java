package com.userservice.authorization.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
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
    @ManyToMany(
            cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
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
