package com.example.personsfinder.application.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.personsfinder.adapter.jpa.persistence.person.JpaPersonRepository;
import com.example.personsfinder.adapter.jpa.persistence.person.PersonEntity;
import com.example.personsfinder.adapter.jpa.persistence.person.mapper.PersonMapper;
import com.example.personsfinder.domain.model.Person;
import com.example.personsfinder.domain.port.PersonRepository;

@Service
public class PersonServiceImpl implements PersonRepository {
    private final JpaPersonRepository jpaRepository;
    private final PersonMapper mapper;

    public PersonServiceImpl(JpaPersonRepository jpaRepository, PersonMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

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

    // This method is not part of the PersonService interface, but it is useful for testing
    public List<Person> findAll() {
        List<Person> persons = new ArrayList<>();
        jpaRepository.findAll().forEach(entity -> persons.add(mapper.toDomain(entity)));
        return persons;
    }
}
