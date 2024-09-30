package com.userservice.authorization.model.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@JsonSerialize(as = User.class)
@AllArgsConstructor
@Builder
@Table(name = "`user`")
public class User extends BaseEntity {
    private String  username;
    private String  password;
    private String  email;
    private String  mobile;
    private String  address;
    private String  city;
    private String  country;
    private boolean isActive;
    private boolean isCredentialsExpired;
    private boolean isAccountLocked;
    private boolean isAccountExpired;
    @ManyToMany(
            cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

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
