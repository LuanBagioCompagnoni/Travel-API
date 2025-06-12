package com.travelapi.travelapi.models;

import java.util.ArrayList;
import java.util.List;

public class Destination {
    private Long id;
    private String name;
    private String location;
    private String description;
    private List<Review> reviews = new ArrayList<>();
    private double averageRating;
    private int totalReviews;

    public Destination() {}

    public Destination(Long id, String name, String location, String description, double averageRating, int totalReviews) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.description = description;
        this.averageRating = averageRating;
        this.totalReviews = totalReviews;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public int getTotalReviews() {
        return totalReviews;
    }

    public void setTotalReviews(int totalReviews) {
        this.totalReviews = totalReviews;
    }
}
