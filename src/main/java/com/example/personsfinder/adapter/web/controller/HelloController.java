package com.example.personsfinder.adapter.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.personsfinder.domain.model.Person;
import com.example.personsfinder.domain.port.PersonRepository;

@RestController
public class HelloController {

    private final PersonRepository personService;

    public HelloController(PersonRepository personService) {
        this.personService = personService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }

    @GetMapping("/test")
    public String testDatabase() {
        // Create a test person
        Person person = new Person("Test User");
        Person savedPerson = personService.save(person);
        
        // Retrieve all persons
        List<Person> allPersons = personService.findAll();
        
        return "Database test successful! Created person: " + savedPerson + ". Total persons: " + allPersons.size();
    }
}
