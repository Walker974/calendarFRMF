package org.calendar.dto;

import lombok.Builder;

@Builder
public record CityDto(Long id, String name) {}
