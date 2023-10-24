package com.example.soulbloom.security;

import com.example.soulbloom.model.User;
import com.example.soulbloom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Custom UserDetailsService implementation for loading User user details.
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    private UserService userService;

    /**
     * Set the UserService for this UserDetailsService.
     *
     * @param userService The UserService to set.
     */
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Load user details by the provided email address.
     *
     * @param emailAddress The email address of the user to load.
     * @return UserDetails for the user with the specified email address.
     * @throws UsernameNotFoundException If the user with the given email address is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
        User user = userService.findUserByEmailAddress(emailAddress);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + emailAddress);
        }
        return new MyUserDetails(user);
    }
}