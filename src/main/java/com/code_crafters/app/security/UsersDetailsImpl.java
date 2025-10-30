package com.code_crafters.app.security;

import com.code_crafters.app.entity.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersDetailsImpl implements UserDetails {

    private Long id;
    private String name;
    private String username;
    private String email;
    
    @JsonIgnore
    private String password;
    
    private String profileImageUrl;


    public static UsersDetailsImpl build(Users user) {
        return new UsersDetailsImpl(
            user.getId(),
            user.getName(),
            user.getUsername(),
            user.getEmail(),
            user.getPassword(),
            user.getProfileImageUrl()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
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