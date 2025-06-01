package com.example.personsfinder.application.usecase.findpersons;

import java.util.List;

import com.example.personsfinder.domain.model.Person;

public interface FindPersonsByIdsUseCase {
    List<Person> invoke(List<Long> ids);
}
