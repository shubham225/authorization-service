package com.userservice.authorization.security.models;

import com.userservice.authorization.models.Role;
import com.userservice.authorization.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
@NoArgsConstructor
@JsonSerialize(as = UserDetails.class)
public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {
    private User user;

    public UserDetails(User user) {
        this.user = user;
    }

    @Override
    @JsonIgnore
    public Collection<? extends org.springframework.security.core.GrantedAuthority> getAuthorities() {
        List<org.springframework.security.core.GrantedAuthority> grantedAuthorities = new ArrayList<>();

        for(Role role : user.getRoles()) {
            grantedAuthorities.add(new GrantedAuthority(role));
        }

        return grantedAuthorities;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return user.isAccountNonExpired();
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return user.isAccountNonLocked();
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return user.isCredentialsNonExpired();
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return user.isActive();
    }
}
