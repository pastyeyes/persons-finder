package com.example.personsfinder.application.usecase.updatelocation;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.personsfinder.adapter.web.controller.advice.exception.NotFoundException;
import com.example.personsfinder.domain.model.Location;
import com.example.personsfinder.domain.model.Person;
import com.example.personsfinder.domain.port.LocationRepository;
import com.example.personsfinder.domain.port.PersonRepository;

@Service
public class UpdatePersonLocationUseCaseImpl implements UpdatePersonLocationUseCase {

    private final PersonRepository personRepository;
    private final LocationRepository locationRepository;

    public UpdatePersonLocationUseCaseImpl(PersonRepository personRepository, LocationRepository locationRepository) {
        this.personRepository = personRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public Location invoke(Long personId, Double latitude, Double longitude) {
        // Validate input parameters
        if (personId == null) {
            throw new IllegalArgumentException("Person ID cannot be null");
        }
        if (latitude == null || longitude == null) {
            throw new IllegalArgumentException("Latitude and longitude cannot be null");
        }
        
        // Check if person exists
        Optional<Person> personOpt = personRepository.findById(personId);
        if (personOpt.isEmpty()) {
            throw new NotFoundException("Person with ID " + personId + " not found");
        }
        
        // Check if location already exists for this person
        Optional<Location> existingLocationOpt = locationRepository.findByReference(personId);
        
        Location locationToSave;
        if (existingLocationOpt.isPresent()) {
            // Update existing location
            Location existingLocation = existingLocationOpt.get();
            locationToSave = new Location(existingLocation.id(), personId, latitude, longitude);
        } else {
            // Create new location
            locationToSave = new Location(personId, latitude, longitude);
        }
        
        // Save and return the location
        return locationRepository.save(locationToSave);
    }
}
