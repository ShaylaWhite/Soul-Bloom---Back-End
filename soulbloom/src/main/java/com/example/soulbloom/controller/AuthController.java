package com.example.soulbloom.controller;

import com.example.soulbloom.model.User;
import com.example.soulbloom.request.LoginRequest;
import com.example.soulbloom.response.LoginResponse;
import com.example.soulbloom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.HashMap;

/**
 * The AuthController class handles authentication operations for users in the Soul Bloom application.
 *
 * @RestController indicates that this class is a Spring REST Controller.
 * @RequestMapping specifies the base URL path for all endpoints in this controller.
 */
@RestController
@RequestMapping(path = "/auth/users") // Base URL path: http://localhost:9092/auth/users
public class AuthController {

    private UserService userService;

    /**
     * Constructor-based autowiring of the UserService dependency.
     *
     * @param userService The UserService implementation to be injected.
     */
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    static HashMap<String, Object> result = new HashMap<>();
    static HashMap<String, Object> message = new HashMap<>();

    // CREATE USER
    /**
     * Endpoint for registering a new user.
     *
     * @param user The User object representing the user to be registered.
     * @return The registered User object.
     */
    @PostMapping("/register") // URL: http://localhost:9092/auth/users/register/
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // LOGIN USER (Post)
    /**
     * Endpoint for authenticating and logging in a user.
     *
     * @param loginRequest The LoginRequest object containing login credentials.
     * @return A ResponseEntity containing a LoginResponse with a JWT token upon successful authentication.
     * Returns UNAUTHORIZED status with an error message if authentication fails.
     */
    @PostMapping("/login") // URL: http://localhost:9092/auth/users/login/
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest) {
        Optional<String> jwtToken = userService.loginUser(loginRequest);
        if (jwtToken.isPresent()) {
            return ResponseEntity.ok(new LoginResponse(jwtToken.get()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse("Authentication failed"));
        }
    }
}
