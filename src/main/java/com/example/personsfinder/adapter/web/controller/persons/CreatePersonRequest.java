package com.example.personsfinder.adapter.web.controller.persons;

import jakarta.validation.constraints.NotBlank;

public record CreatePersonRequest(
    @NotBlank(message = "Name is required")
    String name
) {}
