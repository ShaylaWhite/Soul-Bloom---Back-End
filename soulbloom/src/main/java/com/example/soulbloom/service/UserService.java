package com.example.soulbloom.service;

import com.example.soulbloom.exception.InformationExistException;
import com.example.soulbloom.model.User;
import com.example.soulbloom.repository.UserRepository;
import com.example.soulbloom.request.LoginRequest;
import com.example.soulbloom.security.JWTUtils;
import com.example.soulbloom.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class for managing users.
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder,
                       JWTUtils jwtUtils, @Lazy AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }

    public static User getCurrentLoggedInUser() {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUser();
    }

    /**
     * Create a new user if the email address is unique.
     *
     * @param userObject The user object to create.
     * @return The created user.
     * @throws InformationExistException If a user with the same email address already exists.
     */
    public User createUser(User userObject) {
        if (!userRepository.existsByEmailAddress(userObject.getEmailAddress())) {
            userObject.setPassword(passwordEncoder.encode(userObject.getPassword()));
            return userRepository.save(userObject);
        } else {
            throw new InformationExistException("User with email address " + userObject.getEmailAddress() + " already exists");
        }
    }

    /**
     * Authenticate a user using their email address and password and generate a JWT token upon successful authentication.
     *
     * @param loginRequest The login request containing email and password.
     * @return Optional containing the JWT token if authentication is successful, empty otherwise.
     */
    public Optional<String> loginUser(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(loginRequest.getEmailAddress(), loginRequest.getPassword());
        try {
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
            return Optional.of(jwtUtils.generateJwtToken(myUserDetails));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    /**
     * Retrieve a user by their email address.
     *
     * @param emailAddress The email address of the user to retrieve.
     * @return The user with the specified email address.
     */
    public User findUserByEmailAddress(String emailAddress) {
        return userRepository.findUserByEmailAddress(emailAddress);
    }

    // Add methods for updating user, deleting user, and other user-related operations here.
}
