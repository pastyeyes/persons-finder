package com.example.personsfinder.domain.model;

public record Location(Long id, Long reference, Double latitude, Double longitude) {
    public Location {
        if (reference == null) {
            throw new IllegalArgumentException("Location reference cannot be null");
        }
        if (latitude == null || longitude == null) {
            throw new IllegalArgumentException("Location coordinates cannot be null");
        }
        if (latitude < -90.0 || latitude > 90.0) {
            throw new IllegalArgumentException("Latitude must be between -90 and 90 degrees");
        }
        if (longitude < -180.0 || longitude > 180.0) {
            throw new IllegalArgumentException("Longitude must be between -180 and 180 degrees");
        }
    }

    // constructor for new locations (no ID)
    public Location(Long reference, Double latitude, Double longitude) {
        this(null, reference, latitude, longitude);
    }
    
    // Business logic methods
    public boolean hasId() {
        return id != null;
    }
    
    // Check if this location references a specific person
    public boolean referencesPersonId(Long personId) {
        return reference != null && reference.equals(personId);
    }
}
