package com.example.soulbloom.service;
import com.example.soulbloom.model.User;
import com.example.soulbloom.request.LoginRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/**
 * Service class for managing doctors, patients, and prescriptions.
 */

@Service
public class UserService {
    public User findByEmailAddress(String emailAddress) {
    }

    public User createUser(User user) {
    }

    public Optional<String> loginUser(LoginRequest loginRequest) {
    }
}
