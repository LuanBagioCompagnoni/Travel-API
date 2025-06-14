package com.travelapi.travelapi.services;

import com.travelapi.travelapi.models.Destination;
import com.travelapi.travelapi.models.DestinationDTO;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DestinationService {
    private final Map<Long, Destination> Destinations = new HashMap<>();
    private long idCounter = 1;

    private Destination dtoToEntity(DestinationDTO destinationDTO) {
        Destination destination = new Destination();
        destination.setName(destinationDTO.getName());
        destination.setLocation(destinationDTO.getLocation());
        destination.setDescription(destinationDTO.getDescription());
        return destination;
    }

    public Destination create(DestinationDTO destinationDTO) {
        Destination destination = dtoToEntity(destinationDTO);

        destination.setId(idCounter++);
        Destinations.put(destination.getId(), destination);
        return destination;
    }

    public List<Destination> list() {
        return new ArrayList<>(Destinations.values());
    }

    public List<Destination> getByName(String term) {
        return Destinations.values().stream()
                .filter(d -> d.getName().toLowerCase().contains(term.toLowerCase()) ||
                        d.getLocation().toLowerCase().contains(term.toLowerCase()) ||
                        d.getDescription().toLowerCase().contains(term.toLowerCase()))
                .toList();
    }

    public Destination getById(Long id) {
        return Destinations.get(id);
    }

    public boolean delete(Long id) {
        return Destinations.remove(id) != null;
    }

    public Destination update(Long id, DestinationDTO destinationDTO) {
        Destination newDestination = dtoToEntity(destinationDTO);

        if (Destinations.containsKey(id)) {
            newDestination.setId(id);
            Destinations.put(id, newDestination);
            return newDestination;
        }
        return null;
    }

    public Destination patch(Long id, DestinationDTO destinationDTO) {
        Destination existingDestination = Destinations.get(id);

        if (existingDestination != null) {
            if (destinationDTO.getName() != null) {
                existingDestination.setName(destinationDTO.getName());
            }

            if (destinationDTO.getLocation() != null) {
                existingDestination.setLocation(destinationDTO.getLocation());
            }

            if (destinationDTO.getDescription() != null) {
                existingDestination.setDescription(destinationDTO.getDescription());
            }
            return existingDestination;
        }
        return null;
    }
}