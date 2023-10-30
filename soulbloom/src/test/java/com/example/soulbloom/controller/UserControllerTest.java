package com.example.soulbloom.controller;

import com.example.soulbloom.controller.UserController;
import com.example.soulbloom.exception.InformationNotFoundException;
import com.example.soulbloom.model.Flower;
import com.example.soulbloom.model.Garden;
import com.example.soulbloom.model.User;
import com.example.soulbloom.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    // Define your mock data here
    User USER_RECORD = new User("username", "password", "email@example.com", "John Doe");

    @Test
    public void deleteUserById() throws Exception {
        // Mock the behavior of the userService
        when(userService.deleteUserById(Mockito.any(Long.class))).thenReturn(Optional.of(USER_RECORD));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/users/1/")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.message").value("User deleted successfully"))
                .andDo(print());
    }

    @Test
    public void getUserGardenById() throws Exception {
        // Mock the behavior of the userService
        when(userService.getGardenById(Mockito.any(Long.class))).thenReturn(new Garden());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/gardens/1/")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.message").value("Success"))
                .andDo(print());
    }

    @Test
    public void getUserProfile() throws Exception {
        // Mock the behavior of the userService
        when(userService.findUserById(Mockito.any(Long.class))).thenReturn(USER_RECORD);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/1/")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.username").value(USER_RECORD.getUsername()))
                .andExpect(jsonPath("$.name").value(USER_RECORD.getName()))
                .andExpect(jsonPath("$.email").value(USER_RECORD.getEmailAddress()))
                .andExpect(jsonPath("$.message").value("User profile retrieved successfully"))
                .andDo(print());
    }

    @Test
    public void addFlowerToGarden() throws Exception {
        // Mock the behavior of the userService
        Flower flower = new Flower("Rose", "Red", USER_RECORD, new Garden());

        when(userService.addFlowerToGarden(Mockito.any(Flower.class))).thenReturn(flower);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/add-flower")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(flower)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.message").value("Flower added to the garden successfully"))
                .andExpect(jsonPath("$.data.selfCareType").value(flower.getSelfCareType()))
                .andExpect(jsonPath("$.data.description").value(flower.getDescription()))
                .andDo(print());
    }

    @Test
    public void deleteFlowerFromGarden() throws Exception {
        // Mock the behavior of the userService
        Flower flower = new Flower(1L, "Rose", "Red", USER_RECORD, new Garden());

        when(userService.deleteFlowerFromGarden(Mockito.any(Long.class))).thenReturn(flower);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/users/flowers/1")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.message").value("Flower deleted from the garden successfully"))
                .andExpect(jsonPath("$.data.id").value(flower.getId()))
                .andExpect(jsonPath("$.data.selfCareType").value(flower.getSelfCareType()))
                .andExpect(jsonPath("$.data.description").value(flower.getDescription()))
                .andDo(print());
    }

    @Test
    public void updateFlower() throws Exception {
        // Mock the behavior of the userService
        Flower flower = new Flower(1L, "Rose", "Red", USER_RECORD, new Garden());

        when(userService.updateFlower(Mockito.any(Long.class), Mockito.any(Flower.class))).thenReturn(flower);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/users/flowers/1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(flower)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.message").value("Flower updated successfully"))
                .andExpect(jsonPath("$.data.id").value(flower.getId()))
                .andExpect(jsonPath("$.data.selfCareType").value(flower.getSelfCareType()))
                .andExpect(jsonPath("$.data.description").value(flower.getDescription()))
                .andDo(print());
    }

    @Test
    public void waterGarden() throws Exception {
        // Mock the behavior of the userService
        Garden garden = new Garden(1L);

        when(userService.waterGarden(Mockito.any(Long.class))).thenReturn(garden);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/users/water-garden/1")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.message").value("Garden watered successfully"))
                .andExpect(jsonPath("$.data.id").value(garden.getId()))
                .andDo(print());
    }

    @Test
    public void createGarden() throws Exception {
        // Mock the behavior of the userService
        Garden garden = new Garden(1L);

        when(userService.createGarden()).thenReturn(garden);
        when(userService.waterGarden(Mockito.any(Long.class))).thenReturn(garden);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/create-garden")
                        .contentType("application/json"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.message").value("Garden created and watered successfully"))
                .andExpect(jsonPath("$.data.id").value(garden.getId()))
                .andDo(print());
    }
    // Add more test methods for other operations related to UserController
}
