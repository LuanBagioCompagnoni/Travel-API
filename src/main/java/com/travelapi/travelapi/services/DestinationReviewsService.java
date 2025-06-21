package com.travelapi.travelapi.services;

import com.travelapi.travelapi.exceptions.NotFoundException;
import com.travelapi.travelapi.models.Destination;
import com.travelapi.travelapi.models.Review;
import com.travelapi.travelapi.repositories.DestinationRepository;
import com.travelapi.travelapi.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class DestinationReviewsService {

    private final DestinationService destinationService;
    private final ReviewRepository reviewRepository;
    private final DestinationRepository destinationRepository;

    public DestinationReviewsService(DestinationService destinationService, ReviewRepository reviewRepository, DestinationRepository destinationRepository) {
        this.destinationService = destinationService;
        this.reviewRepository = reviewRepository;
        this.destinationRepository = destinationRepository;
    }

    public Destination reviewDestination(Long id, Review review) {
        Destination destination = destinationService.getById(id);

        if (destination == null) {
            throw new NotFoundException("Destination not found!");

        }
        if(review == null) {
            throw new IllegalArgumentException("Review is required!");
        }

        review.setDestination(destination);
        reviewRepository.save(review);

        destination.getReviews().add(review);

        int totalReviews = destination.getReviews().size();

        double totalRatingsSum = destination.getReviews().stream()
                .mapToInt(Review::getRating)
                .sum();

        double newAverageRating = totalRatingsSum / totalReviews;

        destination.setTotalReviews(totalReviews);
        destination.setAverageRating(newAverageRating);
        destinationRepository.save(destination);


        return destination;
    }

}