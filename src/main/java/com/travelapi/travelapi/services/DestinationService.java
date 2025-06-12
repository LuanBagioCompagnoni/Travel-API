package com.travelapi.travelapi.services;

import com.travelapi.travelapi.models.Destination;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DestinationService {
    private final Map<Long, Destination> Destinations = new HashMap<>();
    private long idCounter = 1;

    public Destination create(Destination destination) {
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
}