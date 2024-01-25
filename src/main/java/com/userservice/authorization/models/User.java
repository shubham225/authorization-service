package com.userservice.authorization.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
    private boolean isCredentialsNonExpired;
    private boolean isAccountNonLocked;
    private boolean isAccountNonExpired;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    //Add extra user info if needed

    public User() {
        this.username = "";
        this.password = "";
        this.isActive = false;
        this.isAccountNonExpired = true;
        this.isAccountNonLocked = true;
        this.isCredentialsNonExpired = true;
        this.roles = new HashSet<>();
    }
}
