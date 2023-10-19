package com.example.soulbloom.model;

import javax.persistence.*;

@Entity
@Table(name = "flowers")
public class Flower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String selfCareType;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Public no-argument constructor
    public Flower() {
    }

    // Constructor with parameters
    public Flower(String selfCareType, String description, User user) {
        this.selfCareType = selfCareType;
        this.description = description;
        this.user = user;
    }

    // Getters and setters for fields
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSelfCareType() {
        return selfCareType;
    }

    public void setSelfCareType(String selfCareType) {
        this.selfCareType = selfCareType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Other methods as required
}
