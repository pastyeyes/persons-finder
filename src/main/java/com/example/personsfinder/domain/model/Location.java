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

    // constructor for auxiliary locations (no ID and no reference - used for search)
    public Location(Double latitude, Double longitude) {
        this(null, null, latitude, longitude);
    }
    
    // Business logic methods
    public boolean hasId() {
        return id != null;
    }
    
    // Check if this location references a specific person
    public boolean referencesPersonId(Long personId) {
        return reference != null && reference.equals(personId);
    }

    /**
     * Calculates the distance to another location in kilometers using https://stackoverflow.com/a/16794680
     * @return the distance in kilometers
     */
    public double distanceToInKm(Location other) {
        if (other == null) {
            throw new IllegalArgumentException("Other location cannot be null.");
        }

        final int EARTH_RADIUS_KM = 6371;

        double latDistance = Math.toRadians(other.latitude - this.latitude);
        double lonDistance = Math.toRadians(other.longitude - this.longitude);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(this.latitude)) * Math.cos(Math.toRadians(other.latitude))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        
        return EARTH_RADIUS_KM * c;
    }
}
