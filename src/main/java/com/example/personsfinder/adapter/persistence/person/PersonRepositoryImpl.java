package com.example.personsfinder.adapter.persistence.person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.personsfinder.adapter.persistence.connector.jpa.JpaPersonRepository;
import com.example.personsfinder.adapter.persistence.connector.jpa.PersonEntity;
import com.example.personsfinder.adapter.persistence.person.mapper.PersonMapper;
import com.example.personsfinder.domain.model.Location;
import com.example.personsfinder.domain.model.Person;
import com.example.personsfinder.domain.port.LocationRepository;
import com.example.personsfinder.domain.port.PersonRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PersonRepositoryImpl implements PersonRepository {
    private final JpaPersonRepository jpaRepository;
    private final LocationRepository locationRepository;
    private final PersonMapper mapper;

    @Override
    public List<Person> findAllById(List<Long> ids) {
        List<Person> persons = new ArrayList<>();
        jpaRepository.findAllById(ids)
            .stream()
            .map(mapper::toDomain)
            .forEach(persons::add);
        return persons;
    }

    @Override
    public Optional<Person> findById(Long id) {
        return jpaRepository.findById(id)
            .map(mapper::toDomain);
    }

    @Override
    public Person save(Person person) {
        if (person == null) {
            throw new IllegalArgumentException("Person cannot be null");
        }

        PersonEntity entity;
        if (person.id() == null) {
            entity = mapper.toEntityForCreation(person);
        } else {
            entity = mapper.toEntity(person);
        }
        
        var saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public List<Person> findAllPersonsWithLocation() {
        List<Person> allPersons = this.findAll();
        List<Location> allLocations = locationRepository.findAll();

        return allPersons.stream()
                .map(person -> {
                    Location personLocation = allLocations.stream()
                            .filter(location -> location.referencesPersonId(person.id()))
                            .findFirst()
                            .orElse(null);
                    // Create a new Person record that includes the location
                    // Assuming Person record has a constructor or a wither method for location
                    // For this example, let's assume Person has a method `withLocation(Location loc)` 
                    // If not, you'll need to adjust Person or create a new DTO that holds Person and Location
                    return new Person(person.id(), person.name(), personLocation); 
                })
                .collect(Collectors.toList());
    }

    // This method is not part of the PersonService interface, but it is useful for testing
    @Override
    public List<Person> findAll() {
        List<Person> persons = new ArrayList<>();
        jpaRepository.findAll().forEach(entity -> persons.add(mapper.toDomain(entity)));
        return persons;
    }
}
