package com.example.personsfinder.adapter.web.controller.locations;

public record LocationResponse(
    Long id, 
    Long personId, 
    Double latitude, 
    Double longitude
) {}
