package org.calendar.dto;

import lombok.Builder;

@Builder
public record TeamDto(Long id, String name) {}