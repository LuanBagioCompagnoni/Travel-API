package com.travelapi.travelapi.models;

public class Review {
    private int rating;
    private String reviewDescription;

    public Review() {
    }

    public Review(int rating, String reviewDescription) {
        this.rating = rating;
        this.reviewDescription = reviewDescription;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReviewDescription() {
        return reviewDescription;
    }

    public void setReviewDescription(String reviewDescription) {
        this.reviewDescription = reviewDescription;
    }
}
