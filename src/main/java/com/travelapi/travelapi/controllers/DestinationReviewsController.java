package com.travelapi.travelapi.controllers;

import com.travelapi.travelapi.models.ApiResponse;
import com.travelapi.travelapi.models.Destination;
import com.travelapi.travelapi.models.Review;
import com.travelapi.travelapi.services.DestinationReviewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/destination-review")
public class DestinationReviewsController {

    private final DestinationReviewsService service;

    public DestinationReviewsController(DestinationReviewsService service) {
        this.service = service;
    }

    @PostMapping("/{id}/review")
    public ResponseEntity<ApiResponse<Destination>> review(@PathVariable Long id, @RequestBody Review review) {
        if (review.getRating() < 1 || review.getRating() > 10) {
            ApiResponse<Destination> response = new ApiResponse<>(false, null, "Invalid rating, must be 1 to 10");

            return ResponseEntity.badRequest().body(response);
        }

        Destination destination = service.reviewDestination(id, review);


        if (destination == null) {
            return ResponseEntity.notFound().build();
        }

        ApiResponse<Destination> response = new ApiResponse<>(true, destination, "Destination Review registred successfully");
        return ResponseEntity.ok(response);
    }
}
