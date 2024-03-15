package com.userservice.authorization.security.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.userservice.authorization.model.Role;
import com.userservice.authorization.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@JsonSerialize(as = MyUserDetails.class)
public class MyUserDetails implements UserDetails {
    private User user;

    public MyUserDetails(User user) {
        this.user = user;
    }
    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<MyGrantedAuthority> grantedAuthorities = new ArrayList<>();

        for(Role role : user.getRoles()) {
            grantedAuthorities.add(new MyGrantedAuthority(role));
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
        return user.getPassword();
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return !user.isAccountExpired();
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return !user.isAccountLocked();
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return !user.isCredentialsExpired();
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return user.isActive();
    }
}
