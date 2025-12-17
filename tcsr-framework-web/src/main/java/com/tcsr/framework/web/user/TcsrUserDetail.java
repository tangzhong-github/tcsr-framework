package com.tcsr.framework.web.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 *
 * @author tangzhong
 * @date   2025-11-07 11:05
 * @since  V1.0.0
 */
@Setter
@AllArgsConstructor
public class TcsrUserDetail implements UserDetails {

    @Getter
    private UserDTO user;

    public Long getUserId() {
        return this.user.getUserId();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.user.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
    }

}