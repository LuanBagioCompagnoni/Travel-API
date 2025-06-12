package com.travelapi.travelapi.services;

import com.travelapi.travelapi.models.Destination;
import com.travelapi.travelapi.models.Review;
import org.springframework.stereotype.Service;

@Service
public class DestinationReviewsService {

    private final DestinationService destinationService;

    public DestinationReviewsService(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    public Destination reviewDestination(Long id, Review review) {
        Destination destination = destinationService.getById(id);

        if (destination != null && review != null) {
            double totalRatingsSum = destination.getReviews().stream()
                    .mapToInt(Review::getRating)
                    .sum();

            int totalReviews = destination.getReviews().size();

            double newAverageRating = totalRatingsSum / totalReviews;


            destination.setTotalReviews(totalReviews);
            destination.setAverageRating(newAverageRating);

        }

        return destination;
    }
}