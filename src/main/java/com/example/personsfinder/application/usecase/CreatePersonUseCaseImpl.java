package com.example.personsfinder.application.usecase;

import org.springframework.stereotype.Service;

import com.example.personsfinder.domain.model.Person;
import com.example.personsfinder.domain.port.PersonRepository;

@Service
public class CreatePersonUseCaseImpl implements CreatePersonUseCase {

    private final PersonRepository personRepository;

    public CreatePersonUseCaseImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person createPerson(String name) {
        // Create domain object (domain validation happens in Person constructor)
        Person person = new Person(name);
        
        // Save through repository
        return personRepository.save(person);
    }
}
