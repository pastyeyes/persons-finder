package com.example.personsfinder.domain.model;

public record Person(Long id, String name, Location location) {
    public Person {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Person name cannot be null or empty");
        }
        name = name.trim();
    }

    // constructor for new persons (no ID)
    public Person(String name) {
        this(null, name, null);
    }

    // constructor for persons with location (no ID)
    public Person(String name, Location location) {
        this(null, name, location);
    }
    
    // Business logic methods
    public boolean hasId() {
        return id != null;
    }

    public Person withLocation(Location location) {
        return new Person(id, name, location);
    }
}
