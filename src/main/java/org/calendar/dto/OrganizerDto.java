package org.calendar.dto;

import lombok.Builder;
import org.calendar.entities.OrganizerType;

@Builder
public record OrganizerDto(Long id, String email, String name, String phoneNumber, OrganizerType organizerType, String logo, String color) {}
