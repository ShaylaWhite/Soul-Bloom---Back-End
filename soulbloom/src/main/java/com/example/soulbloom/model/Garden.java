package com.example.soulbloom.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "gardens")
public class Garden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column
    private Date lastWatered;

    @JsonIgnore
    @OneToMany(mappedBy = "garden", fetch = FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.FALSE)
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

    public Date getLastWatered() {
        return lastWatered;
    }

    public void setLastWatered(Date lastWatered) {
        this.lastWatered = lastWatered;
    }

    public List<Flower> getFlowers() {
        return flowers;
    }

    public void setFlowers(List<Flower> flowers) {
        this.flowers = flowers;
    }

    // Constructor with parameters
    public Garden(User user, Date lastWatered, List<Flower> flowers) {
        this.user = user;
        this.lastWatered = lastWatered;
        this.flowers = flowers;
    }
}
