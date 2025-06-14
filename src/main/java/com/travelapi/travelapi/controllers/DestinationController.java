package com.travelapi.travelapi.controllers;

import com.travelapi.travelapi.models.ApiResponse;
import com.travelapi.travelapi.models.Destination;
import com.travelapi.travelapi.models.DestinationDTO;
import com.travelapi.travelapi.services.DestinationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/destination")
public class DestinationController {

    private final DestinationService service;

    public DestinationController(DestinationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Destination>> create(@RequestBody DestinationDTO destinationDTO) {
        Destination newDestination = service.create(destinationDTO);

        ApiResponse<Destination> response = new ApiResponse<>(true, newDestination, "Destination created successfully!");

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Destination>>> list() {
        List<Destination> destinations = service.list();

        ApiResponse<List<Destination>> response = new ApiResponse<>(true, destinations, null);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/getByTerm")
    public ResponseEntity<ApiResponse<List<Destination>>> getByName(@RequestParam String term) {
        List<Destination> destinations = service.getByName(term);

        ApiResponse<List<Destination>> response =  new ApiResponse<>(true, destinations, null);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Destination>> getById(@PathVariable Long id) {
        Destination destination = service.getById(id);

        if (destination == null) {
            return ResponseEntity.notFound().build();
        }

        ApiResponse<Destination> response = new ApiResponse<>(true, destination, null);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Destination>> update(@PathVariable Long id, @RequestBody DestinationDTO destinationDTO) {
        Destination updated = service.update(id, destinationDTO);

        if (updated == null) {
            return ResponseEntity.notFound().build();
        }

        ApiResponse<Destination> response = new ApiResponse<>(true, updated, "Destination updated successfully!");
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<Destination>> patch(@PathVariable Long id, @RequestBody DestinationDTO destinationDTO) {
        Destination patched = service.patch(id, destinationDTO);

        if (patched == null) {
            return ResponseEntity.notFound().build();
        }

        ApiResponse<Destination> response = new ApiResponse<>(true, patched, "Destination updated partially!");
        return ResponseEntity.ok(response);
    }

}
