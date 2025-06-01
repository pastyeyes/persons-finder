package com.example.personsfinder.adapter.web.controller;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.matchesRegex;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testDatabaseEndpoint() throws Exception {
        mockMvc.perform(get("/test"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Database test successful!")))
                // Regex to match "Created person: Person{id=ANY_NUMBER, name='Test User'}"
                // This assumes the Person.toString() format is "Person{id=<id>, name='<name>'}"
                .andExpect(content().string(matchesRegex(".*Created person: Person\\{id=\\d+, name='Test User'\\}.*")))
                .andExpect(content().string(containsString(". Total persons: 1")));
    }
}
