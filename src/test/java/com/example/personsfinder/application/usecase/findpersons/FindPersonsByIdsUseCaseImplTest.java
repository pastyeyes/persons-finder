package com.example.personsfinder.application.usecase.findpersons;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.personsfinder.domain.model.Person;
import com.example.personsfinder.domain.port.PersonRepository;

@ExtendWith(MockitoExtension.class)
public class FindPersonsByIdsUseCaseImplTest {

    @Mock
    private PersonRepository personRepository;

    private FindPersonsByIdsUseCaseImpl findPersonsByIdsUseCase;

    @BeforeEach
    void setUp() {
        findPersonsByIdsUseCase = new FindPersonsByIdsUseCaseImpl(personRepository);
    }

    @Test
    void shouldFindPersonsSuccessfully() {
        // Given
        List<Long> ids = Arrays.asList(1L, 2L);
        List<Person> expected = Arrays.asList(
            new Person(1L, "John Doe", null),
            new Person(2L, "Jane Smith", null)
        );
        when(personRepository.findAllById(ids)).thenReturn(expected);

        // When
        List<Person> result = findPersonsByIdsUseCase.invoke(ids);

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).name());
        assertEquals("Jane Smith", result.get(1).name());
        verify(personRepository).findAllById(ids);
    }

    @Test
    void shouldThrowExceptionWhenIdsNullOrEmpty() {
        // When & Then
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> findPersonsByIdsUseCase.invoke(null)
        );
        assertEquals("IDs list cannot be null or empty", exception.getMessage());

        // When & Then
        IllegalArgumentException exception2 = assertThrows(
            IllegalArgumentException.class,
            () -> findPersonsByIdsUseCase.invoke(Collections.emptyList())
        );
        assertEquals("IDs list cannot be null or empty", exception2.getMessage());
    }
}
