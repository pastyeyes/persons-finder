package com.example.personsfinder.adapter.jpa.persistence.person;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "persons")
@Getter
@Setter
@AllArgsConstructor
public class PersonEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    public PersonEntity(String name) {
        this.name = name;
    }
}
