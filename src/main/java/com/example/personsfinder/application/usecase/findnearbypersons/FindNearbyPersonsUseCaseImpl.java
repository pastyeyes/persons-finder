package com.example.personsfinder.application.usecase.findnearbypersons;

import com.example.personsfinder.domain.model.Location;
import com.example.personsfinder.domain.model.Person;
import com.example.personsfinder.domain.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FindNearbyPersonsUseCaseImpl implements FindNearbyPersonsUseCase {

    private final PersonService personService;

    @Override
    public List<Person> invoke(Double latitude, Double longitude, Double radiusKm) {
        Location queryLocation = new Location(latitude, longitude);
        // This query has a great impact on performance, as it retrieves ALL persons with locations.
        // Improve this by using the appropriate geospatial database, data type and optimizations techniques (e.g. caching).
        List<Person> allPersons = personService.findAllPersonsWithLocation();

        return allPersons.stream()
          .filter(person -> {
              if (person.location() == null) {
                  return false;
              }
              double distance = queryLocation.distanceToInKm(person.location());
              return distance <= radiusKm;
          })
          // This calculation happening twice is not optimal, but it is done here for simplicity.
          // In a real-world scenario, you would want to calculate the distance once and store it.
          .sorted(Comparator.comparingDouble(person -> queryLocation.distanceToInKm(person.location())))
          .collect(Collectors.toList());
    }
}
