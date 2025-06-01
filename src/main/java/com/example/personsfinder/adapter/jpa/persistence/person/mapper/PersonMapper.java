package com.example.personsfinder.adapter.jpa.persistence.person.mapper;

import org.springframework.stereotype.Component;

import com.example.personsfinder.adapter.jpa.persistence.person.PersonEntity;
import com.example.personsfinder.domain.model.Person;

@Component
public class PersonMapper {
    
    public Person toDomain(PersonEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Person(entity.getId(), entity.getName());
    }
    
    public PersonEntity toEntity(Person domain) {
        if (domain == null) {
            return null;
        }
        return new PersonEntity(domain.id(), domain.name());
    }
    
    public PersonEntity toEntityForCreation(Person domain) {
        if (domain == null) {
            return null;
        }
        // Don't set ID for new entities
        return new PersonEntity(domain.name());
    }
}
