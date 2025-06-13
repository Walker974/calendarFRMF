package org.calendar.dto;

import lombok.Builder;

@Builder
public record EventTypeDto(Long id, String name, String description) {}
