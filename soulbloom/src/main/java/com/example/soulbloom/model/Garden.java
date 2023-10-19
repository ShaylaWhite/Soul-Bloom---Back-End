package com.example.soulbloom.model;

import javax.persistence.*;
import java.security.Timestamp;
import java.util.List;

@Entity
@Table(name = "gardens")
public class Garden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private Timestamp lastWatered;

    @OneToMany(mappedBy = "garden")
    private List<Flower> flowers;



    public Garden() {

    }

    // Constructors, getters, and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getLastWatered() {
        return lastWatered;
    }

    public void setLastWatered(Timestamp lastWatered) {
        this.lastWatered = lastWatered;
    }

    public List<Flower> getFlowers() {
        return flowers;
    }

    public void setFlowers(List<Flower> flowers) {
        this.flowers = flowers;
    }

    // Constructor with parameters
    public Garden(User user, Timestamp lastWatered, List<Flower> flowers) {
        this.user = user;
        this.lastWatered = lastWatered;
        this.flowers = flowers;
    }
}
