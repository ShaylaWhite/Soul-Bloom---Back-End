package com.example.soulbloom.security;

import com.example.soulbloom.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Custom UserDetails implementation for User entities.
 */
public class MyUserDetails implements UserDetails {

    private final User user;

    /**
     * Constructs a new MyUserDetails instance with the provided User entity.
     *
     * @param user The User entity for which to create UserDetails.
     */
    public MyUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        // Add authorities based on custom logic or your requirements
        // Here, a simple ROLE_USER authority is added, but you can customize it as needed.
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmailAddress();
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

    /**
     * Get the User entity associated with this UserDetails.
     *
     * @return The User entity.
     */
    public User getUser() {
        return user;
    }
}
