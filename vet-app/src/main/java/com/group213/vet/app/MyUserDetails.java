package com.group213.vet.app;

import com.group213.vet.app.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

public class MyUserDetails implements UserDetails {

    private String username;
    private String password;
    private boolean active;
    private List<GrantedAuthority> authorities;

    static final String REGEX_USERS = "student"; // exact match
    static final String REGEX_ADMINS = "instructor"; // group OR match
    static final String AUTH_ADMINS = "ADMIN";
    static final String AUTH_USERS = "USER";


    public MyUserDetails(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.active = user.isActive();
        this.authorities = Arrays.stream(
                        user
                                .getTheType()
                                .replaceAll(REGEX_USERS, AUTH_USERS)
                                .replaceAll(REGEX_ADMINS, AUTH_ADMINS)
                                .split(",")
                )
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return authorities;
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
        return active;
    }
}