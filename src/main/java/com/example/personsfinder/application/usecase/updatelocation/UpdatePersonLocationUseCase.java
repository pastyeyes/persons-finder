package com.example.personsfinder.application.usecase.updatelocation;

import com.example.personsfinder.domain.model.Location;

public interface UpdatePersonLocationUseCase {
    Location invoke(Long personId, Double latitude, Double longitude);
}
