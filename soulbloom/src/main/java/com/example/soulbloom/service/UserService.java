package com.example.soulbloom.service;

import com.example.soulbloom.exception.InformationExistException;
import com.example.soulbloom.exception.InformationNotFoundException;
import com.example.soulbloom.model.Flower;
import com.example.soulbloom.model.Garden;
import com.example.soulbloom.model.User;
import com.example.soulbloom.repository.FlowerRepository;
import com.example.soulbloom.repository.GardenRepository;
import com.example.soulbloom.repository.UserRepository;
import com.example.soulbloom.request.LoginRequest;
import com.example.soulbloom.security.JWTUtils;
import com.example.soulbloom.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
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
    private final FlowerRepository flowerRepository; // Inject FlowerRepository
    private final GardenRepository gardenRepository; // Inject GardenRepository

    @Autowired
    public UserService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder,
                       JWTUtils jwtUtils, @Lazy AuthenticationManager authenticationManager,
                       FlowerRepository flowerRepository, GardenRepository gardenRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
        this.flowerRepository = flowerRepository; // Initialize the flowerRepository
        this.gardenRepository = gardenRepository; // Initialize the gardenRepository
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

    //CRUD Methods for user-related operations

    /**
     * Retrieve a user by their user ID.
     *
     * @param userId The ID of the user to retrieve.
     * @return Optional containing the user if found, or empty if not found.
     */
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    /**
     * Update a user's details by their user ID.
     *
     * @param userId      The ID of the user to update.
     * @param updatedUser The updated user object.
     * @return Optional containing the updated user if successful, or empty if the user is not found.
     */
    public Optional<User> updateUserById(Long userId, User updatedUser) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setUsername(updatedUser.getUsername()); // Update the username (name)
            // Add more fields to update as needed
            return Optional.of(userRepository.save(user));
        } else {
            throw new InformationNotFoundException("User with ID " + userId + " not found");
        }
    }

    /**
     * Get the user's garden.
     *
     * @return The user's garden.
     * @throws InformationNotFoundException If the user is not found.
     */
    public User getUserGarden() {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDetails.getUser();
        if (user != null) {
            return user;
        } else {
            throw new InformationNotFoundException("User not found");
        }
    }

    /**
     * Add a flower to the user's garden.
     *
     * @param flower The Flower object representing the flower to be added.
     * @return The added flower.
     * @throws InformationNotFoundException If the user is not found.
     */
    public Flower addFlowerToGarden(Flower flower) {
        User user = getUserGarden();
        if (user != null) {
            flower.setUser(user);
            return flowerRepository.save(flower);
        } else {
            throw new InformationNotFoundException("User not found");
        }
    }

    /**
     * Delete a flower from the user's garden.
     *
     * @param flowerId The ID of the flower to delete.
     * @return The deleted flower.
     * @throws InformationNotFoundException If the flower is not found or does not belong to the user.
     */
    public Flower deleteFlowerFromGarden(Long flowerId) {
        User user = getUserGarden(); // Assuming this method retrieves the current user.
        Long userId = user.getId(); // Get the ID of the current user.
        Optional<Flower> flower = flowerRepository.findByIdAndUser_Id(flowerId, userId);
        if (flower.isPresent()) {
            // Check if the flower belongs to the user.
            Flower flowerToDelete = flower.get();
            if (flowerToDelete.getUser().getId().equals(userId)) {
                flowerRepository.deleteById(flowerId);
                return flowerToDelete;
            } else {
                throw new InformationNotFoundException("Flower with ID " + flowerId + " does not belong to the user.");
            }
        } else {
            throw new InformationNotFoundException("Flower with ID " + flowerId + " not found.");
        }
    }


    /**
     * Delete a user by their user ID.
     *
     * @param userId The ID of the user to delete.
     * @return Optional containing the deleted user if successful, or empty if the user is not found.
     */
    public Optional<User> deleteUserById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            userRepository.deleteById(userId);
            return userOptional;
        } else {
            throw new InformationNotFoundException("User with ID " + userId + " not found");
        }
    }

    /**
     * Get a list of all users.
     *
     * @return List of all users.
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Water the user's garden.
     *
     * @param gardenId The ID of the garden to water.
     * @return The updated garden.
     * @throws InformationNotFoundException If the garden is not found or does not belong to the user.
     */
    public Garden waterGarden(Long gardenId) {
        User user = getUserGarden();
        Optional<Garden> gardenOptional = gardenRepository.findByUser_Id(gardenId);
        if (gardenOptional.isPresent() && gardenOptional.get().getUser().getId().equals(user.getId())) {
            // Implement the logic to water the garden and update the timestamp
            Garden garden = gardenOptional.get();
            garden.setLastWatered(new Date()); // You might want to update this logic
            return gardenRepository.save(garden);
        } else {
            throw new InformationNotFoundException("Garden with ID " + gardenId + " not found or does not belong to the user.");
        }
    }
}
