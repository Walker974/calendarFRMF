package org.calendar.dto;

import org.calendar.entities.OrganizerType;

public record OrganizerDto(Long id, String email, String name, String phoneNumber, OrganizerType organizerType, String logo, String color) {}
