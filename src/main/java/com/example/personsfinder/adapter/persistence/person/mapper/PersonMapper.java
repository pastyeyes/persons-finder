package com.example.personsfinder.adapter.persistence.person.mapper;

import org.springframework.stereotype.Component;

import com.example.personsfinder.adapter.persistence.connector.jpa.LocationEntity;
import com.example.personsfinder.adapter.persistence.connector.jpa.PersonEntity;
import com.example.personsfinder.adapter.persistence.location.mapper.LocationMapper;
import com.example.personsfinder.domain.model.Location;
import com.example.personsfinder.domain.model.Person;

@Component
public class PersonMapper {

    private final LocationMapper locationMapper;

    public PersonMapper(LocationMapper locationMapper) {
        this.locationMapper = locationMapper;
    }
    
    public Person toDomain(PersonEntity entity) {
        if (entity == null) {
            return null;
        }
        Location location = null;
        if (entity.getLocation() != null) {
            location = locationMapper.toDomain(entity.getLocation());
        }
        return new Person(entity.getId(), entity.getName(), location);
    }
    
    public PersonEntity toEntity(Person domain) {
        if (domain == null) {
            return null;
        }
        PersonEntity entity = new PersonEntity();
        entity.setId(domain.id());
        entity.setName(domain.name());

        if (domain.location() != null) {
            LocationEntity locationEntity = locationMapper.toEntity(domain.location());
            // Ensure the PersonEntity is set on the LocationEntity for the bidirectional relationship
            if (locationEntity != null) {
                locationEntity.setPerson(entity); 
            }
            entity.setLocation(locationEntity);
        }
        return entity;
    }
    
    public PersonEntity toEntityForCreation(Person domain) {
        if (domain == null) {
            return null;
        }
        PersonEntity entity = new PersonEntity();
        entity.setName(domain.name()); // ID is not set for creation

        if (domain.location() != null) {
            LocationEntity locationEntity = locationMapper.toEntityForCreation(domain.location());
            // Ensure the PersonEntity is set on the LocationEntity for the bidirectional relationship
            if (locationEntity != null) {
                locationEntity.setPerson(entity);
            }
            entity.setLocation(locationEntity);
        }
        return entity;
    }
}
