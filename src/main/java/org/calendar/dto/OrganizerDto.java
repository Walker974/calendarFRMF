package org.calendar.dto;

import org.calendar.entities.OrganizerType;

public record OrganizerDto(Long id, String email, String phoneNumber, OrganizerType organizerType) {}
