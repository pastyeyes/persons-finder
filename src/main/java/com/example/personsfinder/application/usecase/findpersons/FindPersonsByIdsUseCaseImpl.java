package com.example.personsfinder.application.usecase.findpersons;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.example.personsfinder.domain.model.Person;
import com.example.personsfinder.domain.port.PersonRepository;

@Service
public class FindPersonsByIdsUseCaseImpl implements FindPersonsByIdsUseCase {

    private final PersonRepository personRepository;

    public FindPersonsByIdsUseCaseImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> invoke(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            throw new IllegalArgumentException("IDs list cannot be null or empty");
        }
        
        // Remove null values and duplicates
        List<Long> cleanIds = ids.stream()
            .filter(id -> id != null && id > 0L)
            .distinct()
            .toList();
            
        if (cleanIds.isEmpty()) {
            throw new IllegalArgumentException("No valid IDs provided");
        }
        
        return personRepository.findAllById(cleanIds);
    }
}
