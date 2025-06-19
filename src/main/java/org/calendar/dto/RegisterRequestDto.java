package org.calendar.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.calendar.entities.Organizer;
import org.calendar.enums.Role;

@Builder
public record RegisterRequestDto(
        @NotNull String email,
        @NotNull String password,
        @NotNull String firstName,
        @NotNull String lastName,
        @NotNull OrganizerDto organizer,
        @NotNull String role
        )
{}