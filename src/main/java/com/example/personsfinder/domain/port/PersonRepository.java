package com.example.personsfinder.domain.port;

import java.util.List;
import java.util.Optional;

import com.example.personsfinder.domain.model.Person;

public interface PersonRepository {

    List<Person> findAllById(List<Long> ids);

    Optional<Person> findById(Long id);

    Person save(Person person);

    List<Person> findAll(); // for testing purposes

}
