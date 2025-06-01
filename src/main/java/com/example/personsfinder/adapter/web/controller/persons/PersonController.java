package com.example.personsfinder.adapter.web.controller.persons;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.personsfinder.adapter.web.controller.locations.LocationResponse;
import com.example.personsfinder.adapter.web.controller.locations.UpdateLocationRequest;
import com.example.personsfinder.application.usecase.createperson.CreatePersonUseCase;
import com.example.personsfinder.application.usecase.findpersons.FindPersonsByIdsUseCase;
import com.example.personsfinder.application.usecase.updatelocation.UpdatePersonLocationUseCase;
import com.example.personsfinder.domain.model.Location;
import com.example.personsfinder.domain.model.Person;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/persons")
@Validated
public class PersonController {

    private final CreatePersonUseCase createPersonUseCase;
    private final FindPersonsByIdsUseCase findPersonsByIdsUseCase;
    private final UpdatePersonLocationUseCase updatePersonLocationUseCase;

    @PostMapping
    public ResponseEntity<CreatePersonResponse> createPerson(@Valid @RequestBody CreatePersonRequest request) {
        // Use the use case to handle business logic
        Person savedPerson = createPersonUseCase.invoke(request.name());
        
        // Map domain object to response
        CreatePersonResponse response = new CreatePersonResponse(savedPerson.id(), savedPerson.name());
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<GetPersonResponse>> getPersons(@RequestParam("id") List<Long> ids) {
        // Use the use case to handle business logic
        List<Person> persons = findPersonsByIdsUseCase.invoke(ids);
        
        // Map domain objects to response
        List<GetPersonResponse> responses = persons.stream()
            .map(person -> new GetPersonResponse(person.id(), person.name()))
            .toList();
        
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}/location")
    public ResponseEntity<LocationResponse> updatePersonLocation(
            @PathVariable Long id,
            @Valid @RequestBody UpdateLocationRequest request) {
        
        // Use the use case to handle business logic
        Location savedLocation = updatePersonLocationUseCase.invoke(id, request.latitude(), request.longitude());
        
        // Map domain object to response
        LocationResponse response = new LocationResponse(
            savedLocation.id(), 
            savedLocation.reference(), 
            savedLocation.latitude(), 
            savedLocation.longitude()
        );
        
        return ResponseEntity.ok(response);
    }
}
