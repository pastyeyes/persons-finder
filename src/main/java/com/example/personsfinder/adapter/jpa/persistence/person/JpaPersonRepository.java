package com.example.personsfinder.adapter.jpa.persistence.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPersonRepository extends JpaRepository<PersonEntity, Long> {
}
