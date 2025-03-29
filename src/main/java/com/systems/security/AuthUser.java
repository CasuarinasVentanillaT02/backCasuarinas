package com.systems.security;

import com.systems.dto.UserDTO;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
public class AuthUser implements UserDetails {
    private UserDTO user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(user.getDe_rol()));    //user.getRole().name())
    }

    @Override
    public String getPassword() {
        return user.getDe_clave();
    }

    @Override
    public String getUsername() {
        return user.getDe_alias();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;  // Cambiado de UserDetails.super.isAccountNonExpired()
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
