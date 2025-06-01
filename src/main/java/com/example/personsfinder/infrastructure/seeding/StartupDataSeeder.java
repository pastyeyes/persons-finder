package com.example.personsfinder.infrastructure.seeding;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.personsfinder.domain.model.Location;
import com.example.personsfinder.domain.model.Person;
import com.example.personsfinder.domain.port.LocationRepository;
import com.example.personsfinder.domain.port.PersonRepository;

@Component
public class StartupDataSeeder implements ApplicationRunner {
    
    private static final Logger logger = LoggerFactory.getLogger(StartupDataSeeder.class);
    private final PersonRepository personRepository;
    private final LocationRepository locationRepository;
    
    public StartupDataSeeder(PersonRepository personRepository, LocationRepository locationRepository) {
        this.personRepository = personRepository;
        this.locationRepository = locationRepository;
    }
    
    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        try {
            logger.info("Starting test data seeding...");
            seedTestData();
            logger.info("Test data seeding completed successfully!");
        } catch (Exception e) {
            logger.error("Error during startup data seeding", e);
        }
    }

    private void seedTestData() {
        logger.info("Seeding test data...");
        createTestPerson("John Doe", 40.7128, -74.0060);
        createTestPerson("Jane Smith", 51.5074, -0.1278);
        createTestPerson("Bob Johnson", 35.6762, 139.6503);
        createTestPerson("Alice Brown", 48.8566, 2.3522);
        createTestPerson("Charlie Wilson", -33.8688, 151.2093);
        createTestPerson("Diana Davis", 52.5200, 13.4050);
        createTestPerson("Eva Miller", 43.6532, -79.3832);
        createTestPerson("Frank Garcia", 40.4168, -3.7038);
        createTestPerson("Grace Martinez", 41.9028, 12.4964);
        createTestPerson("Henry Anderson", 52.3676, 4.9041);
    }

    private void createTestPerson(String fullName, double latitude, double longitude) {
        var person = personRepository.save(new Person(fullName));
        locationRepository.save(new Location(person.id(), latitude, longitude));
        logger.debug("Created person: {} (lat: {}, lon: {})", fullName, latitude, longitude);
    }
}
