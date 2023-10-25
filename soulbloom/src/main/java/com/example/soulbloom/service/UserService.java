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
 * Service class for managing users, flowers, and gardens.
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final FlowerRepository flowerRepository;
    private final GardenRepository gardenRepository;

    @Autowired
    public UserService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder,
                       JWTUtils jwtUtils, @Lazy AuthenticationManager authenticationManager,
                       FlowerRepository flowerRepository, GardenRepository gardenRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
        this.flowerRepository = flowerRepository;
        this.gardenRepository = gardenRepository;
    }

    // User-related methods

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
            throw new InformationExistException("User with email address " + userObject.getEmailAddress() + " already exists.");
        }
    }

    /**
     * Authenticate a user using their email address and password.
     *
     * @param loginRequest The login request containing email and password.
     * @return Optional containing the JWT token if authentication is successful, empty otherwise.
     */
    public Optional<String> loginUser(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmailAddress(), loginRequest.getPassword());

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

    /**
     * Delete a user by their user ID.
     *
     * @param userId The ID of the user to delete.
     * @return Optional containing the deleted user if successful, or empty if the user is not found.
     * @throws InformationNotFoundException If the user is not found.
     */
    public Optional<User> deleteUserById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Delete associated gardens
            List<Garden> userGardens = user.getGardens();
            if (userGardens != null) {
                for (Garden garden : userGardens) {
                    gardenRepository.delete(garden);
                }
            }

            // Now, delete the user
            userRepository.deleteById(userId);

            return userOptional;
        } else {
            throw new InformationNotFoundException("User with ID " + userId + " not found.");
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

    // Flower-related methods

    /**
     * Add a flower to the user's garden.
     *
     * @param flower The Flower object representing the flower to be added.
     * @return The added flower.
     * @throws InformationNotFoundException If the user is not found.
     */
    public Flower addFlowerToGarden(Flower flower) {
        User user = getUserGarden().getUser();
        if (user != null) {
            flower.setUser(user);
            List<Garden> gardens = user.getGardens();
            if (gardens != null && !gardens.isEmpty()) {
                Garden userGarden = gardens.get(0);
                flower.setGarden(userGarden);
            } else {
                // Handle the case where there's no garden for the user
                // You might create a garden for the user here if needed
            }
            return flowerRepository.save(flower);
        } else {
            throw new InformationNotFoundException("User not found.");
        }
    }

    /**
     * Update an existing flower with new data.
     *
     * @param flowerId         The ID of the flower to update.
     * @param updatedFlowerData The new data for the flower.
     * @return The updated flower.
     * @throws InformationNotFoundException If the flower is not found.
     */
    public Flower updateFlower(Long flowerId, Flower updatedFlowerData) {
        Flower flower = getExistingFlowerById(flowerId);
        updateFlowerAttributes(flower, updatedFlowerData);
        return flowerRepository.save(flower);
    }


    /**
     * Get an existing flower by its ID.
     *
     * @param flowerId The ID of the flower to retrieve.
     * @return The existing flower.
     * @throws InformationNotFoundException If the flower is not found.
     */
    private Flower getExistingFlowerById(Long flowerId) {
        Optional<Flower> flowerOptional = flowerRepository.findById(flowerId);
        if (flowerOptional.isPresent()) {
            return flowerOptional.get();
        } else {
            throw new InformationNotFoundException("Flower with ID " + flowerId + " not found.");
        }
    }

    /**
     * Update specific attributes of a flower with new data.
     *
     * @param flower           The existing flower to update.
     * @param updatedFlowerData The new data for the flower.
     */
    private void updateFlowerAttributes(Flower flower, Flower updatedFlowerData) {
        if (updatedFlowerData.getDescription() != null) {
            flower.setDescription(updatedFlowerData.getDescription());
        }
        if (updatedFlowerData.getSelfCareType() != null) {
            flower.setSelfCareType(updatedFlowerData.getSelfCareType());
        }
        // Add more attribute updates as needed
    }
    /**
     * Delete a flower from the user's garden.
     *
     * @param flowerId The ID of the flower to delete.
     * @return The deleted flower.
     * @throws InformationNotFoundException If the flower is not found or does not belong to the user.
     */
    public Flower deleteFlowerFromGarden(Long flowerId) {
        Flower flower = getExistingFlowerById(flowerId);
        if (flower.getUser().getId().equals(getCurrentUser().getId())) {
            flowerRepository.delete(flower);
            return flower;
        } else {
            throw new InformationNotFoundException("Flower with ID " + flowerId + " does not belong to the user.");
        }
    }


    // Garden-related methods

    /**
     * Water the user's garden.
     *
     * @param gardenId The ID of the garden to water.
     * @return The updated garden.
     * @throws InformationNotFoundException If the garden is not found or does not belong to the user.
     */
    public Garden waterGarden(Long gardenId) {
        User user = getUserGarden().getUser();
        Optional<Garden> gardenOptional = gardenRepository.findByUser_Id(gardenId);
        Garden garden;
        if (gardenOptional.isPresent() && gardenOptional.get().getUser().getId().equals(user.getId())) {
            garden = gardenOptional.get();
        } else {
            garden = new Garden();
            garden.setUser(user);
        }
        garden.setLastWatered(new Date());
        return gardenRepository.save(garden);
    }

    /**
     * Create a new garden for the user.
     *
     * @return The newly created garden.
     */
    public Garden createGarden() {
        User user = getCurrentUser();
        Garden garden = new Garden();
        garden.setUser(user);
        garden.setLastWatered(new Date());
        return gardenRepository.save(garden);
    }

    /**
     * Retrieve the user's garden.
     *
     * @return The user's garden.
     * @throws InformationNotFoundException If the user's garden is not found.
     */
    public Garden getUserGarden() {
        User user = getCurrentUser();
        Optional<Garden> gardenOptional = gardenRepository.findByUser_Id(user.getId());
        if (gardenOptional.isPresent()) {
            return gardenOptional.get();
        } else {
            throw new InformationNotFoundException("Garden not found for the user.");
        }
    }

    /**
     * Get the current user.
     *
     * @return The current user.
     * @throws InformationNotFoundException If the user is not found.
     */
    public User getCurrentUser() {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDetails.getUser();
        if (user != null) {
            return user;
        } else {
            throw new InformationNotFoundException("User not found");
        }
    }

    /**
     * Get a garden by its ID.
     *
     * @param gardenId The ID of the garden to retrieve.
     * @return The garden with the specified ID.
     */
    public Garden getGardenById(Long gardenId) {
        return gardenRepository.findById(gardenId).orElse(null);
    }
}
