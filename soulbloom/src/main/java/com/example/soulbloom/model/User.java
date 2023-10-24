package com.example.soulbloom.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(unique = true, nullable = false)
    private String emailAddress;

    // Define a relationship mapping to represent the "User can have many Flowers" relationship.
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Flower> flowers;

    // Constructors
    public User() {
        // Default constructor
    }

    public User(String username, String password, String emailAddress) {
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
    }

    public User(String username, String password, String emailAddress, List<Flower> flowers) {
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
        this.flowers = flowers;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public List<Flower> getFlowers() {
        return flowers;
    }

    public void setFlowers(List<Flower> flowers) {
        this.flowers = flowers;
    }

    // Additional methods, if needed

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }
}
