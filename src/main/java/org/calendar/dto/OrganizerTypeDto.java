package org.calendar.dto;

import lombok.Builder;

@Builder
public record OrganizerTypeDto(Long id, String name, String description, int priority) {
}
