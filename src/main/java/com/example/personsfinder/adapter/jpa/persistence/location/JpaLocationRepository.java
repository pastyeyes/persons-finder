package com.example.personsfinder.adapter.jpa.persistence.location;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaLocationRepository extends JpaRepository<LocationEntity, Long> {
    
    Optional<LocationEntity> findByReference(Long reference);
    
}
