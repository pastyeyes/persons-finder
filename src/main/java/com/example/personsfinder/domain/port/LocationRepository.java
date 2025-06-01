package com.example.personsfinder.domain.port;

import java.util.List;
import java.util.Optional;

import com.example.personsfinder.domain.model.Location;

public interface LocationRepository {
    
    Optional<Location> findByReference(Long reference);

    Location save(Location location);

    List<Location> findAll(); // for testing purposes

}
