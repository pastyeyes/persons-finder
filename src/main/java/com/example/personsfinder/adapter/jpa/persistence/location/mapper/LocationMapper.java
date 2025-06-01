package com.example.personsfinder.adapter.jpa.persistence.location.mapper;

import org.springframework.stereotype.Component;

import com.example.personsfinder.adapter.jpa.persistence.location.LocationEntity;
import com.example.personsfinder.domain.model.Location;

@Component
public class LocationMapper {
    
    public Location toDomain(LocationEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Location(entity.getId(), entity.getReference(), entity.getLatitude(), entity.getLongitude());
    }
    
    public LocationEntity toEntity(Location domain) {
        if (domain == null) {
            return null;
        }
        return new LocationEntity(domain.id(), domain.reference(), domain.latitude(), domain.longitude());
    }
    
    public LocationEntity toEntityForCreation(Location domain) {
        if (domain == null) {
            return null;
        }
        // Don't set ID for new entities
        return new LocationEntity(domain.reference(), domain.latitude(), domain.longitude());
    }
}
