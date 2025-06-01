package com.example.personsfinder.application.usecase.createperson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.personsfinder.domain.model.Person;
import com.example.personsfinder.domain.port.PersonRepository;

@ExtendWith(MockitoExtension.class)
public class CreatePersonUseCaseImplTest {

    @Mock
    private PersonRepository personRepository;

    private CreatePersonUseCaseImpl createPersonUseCase;

    @BeforeEach
    void setUp() {
        createPersonUseCase = new CreatePersonUseCaseImpl(personRepository);
    }

    @Test
    void shouldCreatePersonSuccessfully() {
        // Given
        String name = "John Doe";
        Person expected = new Person(1L, "John Doe");
        when(personRepository.save(any(Person.class))).thenReturn(expected);

        // When
        Person result = createPersonUseCase.invoke(name);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals("John Doe", result.name());
        verify(personRepository).save(any(Person.class));
    }

    @Test
    void shouldThrowExceptionWhenNameIsNullOrEmptyOrBlank() {
        assertThrows(IllegalArgumentException.class, () -> createPersonUseCase.invoke(null));
        assertThrows(IllegalArgumentException.class, () -> createPersonUseCase.invoke(""));
        assertThrows(IllegalArgumentException.class, () -> createPersonUseCase.invoke("   "));
    }
}
