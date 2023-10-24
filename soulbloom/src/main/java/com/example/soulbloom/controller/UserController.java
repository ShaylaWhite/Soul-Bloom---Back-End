package com.example.soulbloom.controller;

import com.example.soulbloom.model.Flower;
import com.example.soulbloom.model.Garden;
import com.example.soulbloom.model.User;
import com.example.soulbloom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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



    /**
     * Endpoint for retrieving all users.
     *
     * @return A ResponseEntity containing a list of users or a not found message.
     *
     * GET: /api/users/
     */
    @GetMapping("/")
    public ResponseEntity<?> getAllUsers() {
        List<User> userList = userService.getAllUsers();
        if (userList.isEmpty()) {
            message.put("message", "No users found");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            message.put("message", "Success");
            message.put("data", userList);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }

    /**
     * Endpoint for retrieving a user by their ID.
     *
     * @param userId The ID of the user to retrieve.
     * @return A ResponseEntity containing the user data or a not found message.
     *
     * GET: /api/users/{userId}
     *
     * @param userId The ID of the user to retrieve.
     */
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable(value = "userId") Long userId) {
        Optional<User> userOptional = userService.getUserById(userId);
        if (userOptional.isPresent()) {
            message.put("message", "Success");
            message.put("data", userOptional.get());
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            message.put("message", "User not found");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Endpoint for updating a user's information by their ID.
     *
     * @param userId      The ID of the user to update.
     * @param updatedUser The updated User object.
     * @return A ResponseEntity indicating success or not found along with a message and data.
     *
     * PUT: /api/users/{userId}
     *
     * @param userId      The ID of the user to update.
     * @param updatedUser The updated User object.
     */
    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUserById(@PathVariable(value = "userId") Long userId, @RequestBody User updatedUser) {
        Optional<User> userOptional = userService.updateUserById(userId, updatedUser);
        if (userOptional.isPresent()) {
            message.put("message", "User updated successfully");
            message.put("data", userOptional.get());
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            message.put("message", "User not found");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Endpoint for deleting a user by their ID.
     *
     * @param userId The ID of the user to delete.
     * @return A ResponseEntity indicating success or not found along with a message and data.
     *
     * DELETE: /api/users/{userId}
     *
     * @param userId The ID of the user to delete.
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable(value = "userId") Long userId) {
        Optional<User> userOptional = userService.deleteUserById(userId);
        if (userOptional.isPresent()) {
            message.put("message", "User deleted successfully");
            message.put("data", userOptional.get());
            return  new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            message.put("message", "User not found");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Endpoint for adding a Flower to the Garden.
     *
     * @param flower The Flower object representing the flower to be added.
     * @return A ResponseEntity indicating success or conflict along with a message and data.
     *
     * POST: /api/users/flowers
     *
     * @param flower The Flower object representing the flower to be added.
     */
    @PostMapping("/flowers")
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
     * Endpoint for deleting a Flower from the Garden.
     *
     * @param flowerId The ID of the flower to be deleted.
     * @return A ResponseEntity indicating success or not found along with a message and data.
     *
     * DELETE: /api/users/flowers/{flowerId}
     *
     * @param flowerId The ID of the flower to be deleted.
     */
    @DeleteMapping("/flowers/{flowerId}")
    public ResponseEntity<?> deleteFlowerFromGarden(@PathVariable(value = "flowerId") Long flowerId) {
        Flower deletedFlower = userService.deleteFlowerFromGarden(flowerId);
        if (deletedFlower != null) {
            message.put("message", "Flower deleted from the garden successfully");
            message.put("data", deletedFlower);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            message.put("message", "Deleting flower from the garden failed");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Endpoint for watering the Garden.
     *
     * @param gardenId The ID of the garden to be watered.
     * @return A ResponseEntity indicating success or conflict along with a message and data.
     *
     * PUT: /api/users/water-garden/{gardenId}
     *
     * @param gardenId The ID of the garden to be watered.
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
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
    }
}