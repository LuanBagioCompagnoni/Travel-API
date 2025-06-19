package com.travelapi.travelapi.services;

import com.travelapi.travelapi.models.Destination;
import com.travelapi.travelapi.dto.DestinationDTO;
import com.travelapi.travelapi.repositories.DestinationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DestinationService {

    private final DestinationRepository repository;

    public DestinationService(DestinationRepository repository) {
        this.repository = repository;
    }

    private Destination dtoToEntity(DestinationDTO dto) {
        Destination destination = new Destination();
        destination.setName(dto.getName());
        destination.setLocation(dto.getLocation());
        destination.setDescription(dto.getDescription());
        return destination;
    }

    public Destination create(DestinationDTO dto) {
        Destination destination = dtoToEntity(dto);
        destination.setAverageRating(0);
        destination.setTotalReviews(0);
        return repository.save(destination);
    }

    public List<Destination> list() {
        return repository.findAll();
    }

    public List<Destination> getByTerm(String term) {
        return repository.searchByTerm(term);
    }

    public Destination getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public boolean delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public Destination update(Long id, DestinationDTO dto) {
        Optional<Destination> existingOpt = repository.findById(id);

        if (existingOpt.isPresent()) {
            Destination destination = dtoToEntity(dto);
            destination.setId(id);
            destination.setAverageRating(existingOpt.get().getAverageRating());
            destination.setTotalReviews(existingOpt.get().getTotalReviews());
            destination.setReviews(existingOpt.get().getReviews());
            return repository.save(destination);
        }

        return null;
    }

    public Destination patch(Long id, DestinationDTO dto) {
        Optional<Destination> existingOpt = repository.findById(id);

        if (existingOpt.isPresent()) {
            Destination destination = existingOpt.get();

            if (dto.getName() != null) destination.setName(dto.getName());
            if (dto.getLocation() != null) destination.setLocation(dto.getLocation());
            if (dto.getDescription() != null) destination.setDescription(dto.getDescription());

            return repository.save(destination);
        }

        return null;
    }
}
