package com.example.personsfinder.adapter.web.controller.persons;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.personsfinder.application.usecase.createperson.CreatePersonUseCase;
import com.example.personsfinder.domain.model.Person;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/persons")
@Validated
public class PersonController {

    private final CreatePersonUseCase createPersonUseCase;

    @PostMapping
    public ResponseEntity<CreatePersonResponse> createPerson(@Valid @RequestBody CreatePersonRequest request) {
        // Use the use case to handle business logic
        Person savedPerson = createPersonUseCase.invoke(request.name());
        
        // Map domain object to response
        CreatePersonResponse response = new CreatePersonResponse(savedPerson.id(), savedPerson.name());
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
