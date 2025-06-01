package com.example.personsfinder.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class LocationTest {

    @Test
    void shouldCreateLocationSuccessfully() {
        // Given
        Long reference = 1L;
        Double latitude = 40.7128;
        Double longitude = -74.0060;

        // When
        Location location = new Location(reference, latitude, longitude);

        // Then
        assertNotNull(location);
        assertEquals(reference, location.reference());
        assertEquals(latitude, location.latitude());
        assertEquals(longitude, location.longitude());
        assertFalse(location.hasId());
    }

    @Test
    void shouldThrowExceptionForOutOfRangeCoordinates() {
        // Latitude out of range
        IllegalArgumentException latEx = assertThrows(
            IllegalArgumentException.class,
            () -> new Location(1L, 91.0, 0.0)
        );
        assertEquals("Latitude must be between -90 and 90 degrees", latEx.getMessage());

        // Longitude out of range
        IllegalArgumentException lonEx = assertThrows(
            IllegalArgumentException.class,
            () -> new Location(1L, 0.0, 181.0)
        );
        assertEquals("Longitude must be between -180 and 180 degrees", lonEx.getMessage());
    }
}
