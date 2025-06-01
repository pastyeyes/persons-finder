package com.example.personsfinder.adapter.persistence.location;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.personsfinder.adapter.persistence.connector.jpa.JpaLocationRepository;
import com.example.personsfinder.adapter.persistence.connector.jpa.LocationEntity;
import com.example.personsfinder.adapter.persistence.location.mapper.LocationMapper;
import com.example.personsfinder.domain.model.Location;
import com.example.personsfinder.domain.port.LocationRepository;

@Repository
public class LocationRepositoryImpl implements LocationRepository {
    private final JpaLocationRepository jpaRepository;
    private final LocationMapper mapper;

    // jpaRepository gives access to the database, and mapper converts between domain model and entity
    public LocationRepositoryImpl(JpaLocationRepository jpaRepository, LocationMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }
    
    @Override
    public Optional<Location> findByReference(Long reference) {
        return jpaRepository.findByReference(reference)
            .map(mapper::toDomain);
    }

    @Override
    public Location save(Location location) {
        if (location == null) {
            throw new IllegalArgumentException("Location cannot be null");
        }

        LocationEntity entity;
        if (location.id() == null) {
            entity = mapper.toEntityForCreation(location);
        } else {
            entity = mapper.toEntity(location);
        }
        
        var saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    // This method is not part of the LocationRepository interface, but it is useful for testing
    @Override
    public List<Location> findAll() {
        List<Location> locations = new ArrayList<>();
        jpaRepository.findAll().forEach(entity -> locations.add(mapper.toDomain(entity)));
        return locations;
    }
}
