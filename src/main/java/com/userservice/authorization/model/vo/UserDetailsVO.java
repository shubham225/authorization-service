package com.userservice.authorization.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.userservice.authorization.model.entity.Role;
import com.userservice.authorization.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@JsonSerialize(as = UserDetailsVO.class)
public class UserDetailsVO implements UserDetails {
    private User user;

    public UserDetailsVO(User user) {
        this.user = user;
    }
    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthorityVO> grantedAuthorities = new ArrayList<>();

        for(Role role : user.getRoles()) {
            grantedAuthorities.add(new GrantedAuthorityVO(role));
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
