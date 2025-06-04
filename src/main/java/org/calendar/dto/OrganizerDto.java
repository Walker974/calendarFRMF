package org.calendar.dto;

import org.calendar.entities.OrganizerType;

public record OrganizerDto(Long id, String name, String email, String phoneNumber, OrganizerType organizerType) {}
