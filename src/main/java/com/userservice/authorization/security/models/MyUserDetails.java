package com.userservice.authorization.security.models;

/**
 * This class will provide user info to spring auth server to validate user login, custom user class is used to store user info in database.
 */

import com.userservice.authorization.models.Role;
import com.userservice.authorization.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
@NoArgsConstructor
@JsonSerialize(as = MyUserDetails.class)
public class MyUserDetails implements UserDetails {
    private User user;

    public MyUserDetails(User user) {
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
