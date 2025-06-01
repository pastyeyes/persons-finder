package com.example.personsfinder.domain.model;

public record Person(Long id, String name) {
    public Person {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Person name cannot be null or empty");
        }
        name = name.trim();
    }

    // constructor for new persons (no ID)
    public Person(String name) {
        this(null, name);
    }
    
    // Business logic methods
    public boolean hasId() {
        return id != null;
    }
    
    // override default toString
    @Override
    public String toString() {
        return String.format("Person{id=%d, name='%s'}", id, name);
    }
}
