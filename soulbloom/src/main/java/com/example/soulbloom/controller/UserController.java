package com.example.soulbloom.controller;

import com.example.soulbloom.exception.InformationNotFoundException;
import com.example.soulbloom.model.Flower;
import com.example.soulbloom.model.Garden;
import com.example.soulbloom.model.User;
import com.example.soulbloom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.soulbloom.request.LoginRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * The UserController class handles user-related operations in the Soul Bloom application.
 *
 * @RestController indicates that this class is a Spring REST Controller.
 * @RequestMapping specifies the base URL path for all endpoints in this controller.
 */
@RestController
@RequestMapping(path = "/api/users/")
public class UserController {

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

    static HashMap<String, Object> message = new HashMap<>();

    // User-related methods

    /**
     * Delete a user by their ID.
     *
     * @param userId The ID of the user to be deleted.
     * @return A ResponseEntity containing the status and, if successful, the deleted user.
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable(value = "userId") Long userId) {
        Optional<User> userOptional = userService.deleteUserById(userId);
        if (userOptional.isPresent()) {
            message.put("message", "User deleted successfully");
            message.put("data", userOptional.get());
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            message.put("message", "User not found");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get a user's garden by its ID.
     *
     * @param gardenId The ID of the garden to retrieve.
     * @return A ResponseEntity containing the status and, if found, the user's garden.
     */
    @GetMapping("/gardens/{gardenId}")
    public ResponseEntity<?> getUserGardenById(@PathVariable Long gardenId) {
        Garden garden = userService.getGardenById(gardenId);
        if (garden != null) {
            message.put("message", "Success");
            message.put("data", garden);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            message.put("message", "User's garden not found.");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get a user's profile by their ID.
     *
     * @param userId The ID of the user to retrieve.
     * @return A ResponseEntity containing the user's profile information.
     */
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserProfile(@PathVariable Long userId) {
        User user = userService.findUserById(userId);
        if (user != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("username", user.getUsername());
            response.put("name", user.getName()); // Include name in the response
            response.put("email", user.getEmailAddress());
            response.put("message", "User profile retrieved successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            message.put("message", "User not found");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    // Flower-related methods

    /**
     * Add a flower to a user's garden.
     *
     * @param flower The Flower object to be added.
     * @return A ResponseEntity containing the status and, if successful, the added flower.
     */
    @PostMapping("/add-flower")
    public ResponseEntity<?> addFlowerToGarden(@RequestBody Flower flower) {
        Flower addedFlower = userService.addFlowerToGarden(flower);
        if (addedFlower != null) {
            message.put("message", "Flower added to the garden successfully");
            message.put("data", addedFlower);
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } else {
            message.put("message", "Adding flower to the garden failed");
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
    }

    /**
     * Delete a flower from a user's garden by its ID.
     *
     * @param flowerId The ID of the flower to be deleted.
     * @return A ResponseEntity containing the status and, if successful, the deleted flower.
     */
    @DeleteMapping("/flowers/{flowerId}")
    public ResponseEntity<?> deleteFlowerFromGarden(@PathVariable(value = "flowerId") Long flowerId) {
        try {
            Flower deletedFlower = userService.deleteFlowerFromGarden(flowerId);
            message.put("message", "Flower deleted from the garden successfully");
            message.put("data", deletedFlower);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (InformationNotFoundException e) {
            message.put("message", e.getMessage());
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Update a flower in a user's garden by its ID.
     *
     * @param flowerId          The ID of the flower to be updated.
     * @param updatedFlowerData The updated Flower object data.
     * @return A ResponseEntity containing the status and, if successful, the updated flower.
     */
    @PutMapping("/flowers/{flowerId}")
    public ResponseEntity<?> updateFlower(@PathVariable(value = "flowerId") Long flowerId, @RequestBody Flower updatedFlowerData) {
        try {
            Flower updatedFlower = userService.updateFlower(flowerId, updatedFlowerData);
            message.put("message", "Flower updated successfully");
            message.put("data", updatedFlower);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (InformationNotFoundException e) {
            message.put("message", e.getMessage());
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    // Garden-related methods

    /**
     * Water a user's garden by its ID.
     *
     * @param gardenId The ID of the garden to be watered.
     * @return A ResponseEntity containing the status and, if successful, the watered garden.
     */
    @PutMapping("/water-garden/{gardenId}")
    public ResponseEntity<?> waterGarden(@PathVariable(value = "gardenId") Long gardenId) {
        Garden wateredGarden = userService.waterGarden(gardenId);
        if (wateredGarden != null) {
            message.put("message", "Garden watered successfully");
            message.put("data", wateredGarden);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            message.put("message", "Watering the garden failed");
            return a new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
    }

    /**
     * Create a new garden for a user.
     *
     * @return A ResponseEntity containing the status and, if successful, the created and watered garden.
     */
    @PostMapping("/create-garden")
    public ResponseEntity<?> createGarden() {
        Garden newGarden = userService.createGarden();
        if (newGarden != null) {
            Garden wateredGarden = userService.waterGarden(newGarden.getId());
            if (wateredGarden != null) {
                message.put("message", "Garden created and watered successfully");
                message.put("data", wateredGarden);
                return new ResponseEntity<>(message, HttpStatus.CREATED);
            } else {
                message.put("message", "Watering the garden failed");
                return new ResponseEntity<>(message, HttpStatus.CONFLICT);
            }
        } else {
            message.put("message", "Creating the garden failed");
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
    }
}
