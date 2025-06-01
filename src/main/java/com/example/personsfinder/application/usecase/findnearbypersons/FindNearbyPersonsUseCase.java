package com.example.personsfinder.application.usecase.findnearbypersons;

import java.util.List;

import com.example.personsfinder.domain.model.Person;

public interface FindNearbyPersonsUseCase {
    List<Person> invoke(Double latitude, Double longitude, Double radiusKm);
}
