package com.example.personsfinder.application.usecase.createperson;

import com.example.personsfinder.domain.model.Person;

public interface CreatePersonUseCase {
    Person invoke(String name);
}
