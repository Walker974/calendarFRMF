package org.calendar.dto;

import lombok.Builder;

@Builder
public record CompetitionDto(Long id, String name) {
}
