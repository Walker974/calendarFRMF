package org.calendar.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record CityDto(Long id, @NotNull(message = "City name cannot be null")
        @Size(min = 3, max = 100, message = "City name must be between 3 and 100 characters")
String name) {}
