package com.example.personsfinder.adapter.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.personsfinder.domain.model.Person;
import com.example.personsfinder.domain.port.PersonRepository;

@RestController
public class HelloController {

    private final PersonRepository personRepository;

    // Constructor injection for the PersonRepository
    // On other controllers, this would be ommited in favour of lombok @RequiredArgsConstructor
    public HelloController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }

    @GetMapping("/test")
    public String testDatabase() {
            // Load all existing persons from database
            List<Person> allPersons = personRepository.findAll();
            return "Database test successful! Total persons: " + allPersons.size();
    }
}
