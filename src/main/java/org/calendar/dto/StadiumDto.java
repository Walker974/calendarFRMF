package org.calendar.dto;

import lombok.Builder;
import org.calendar.entities.City;

@Builder
public record StadiumDto(Long id, String name, City city) {}