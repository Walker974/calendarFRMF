package org.calendar.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public record StadiumEventDto(
        Long id,
        @NotNull(message = "Stadium ID cannot be null")
        Long stadiumId,
        String stadiumName,
        String stadiumCity,
        @NotNull(message = "Event name cannot be null")
        @Size(min = 3, max = 100, message = "Event name must be between 3 and 100 characters")
        String eventName,
        @NotNull(message = "Start time cannot be null")
        LocalDateTime startTime,
        @NotNull(message = "End time cannot be null")
        LocalDateTime endTime,
        @NotNull(message = "Description cannot be null")
        @Size(min = 10, max = 500, message = "Description must be between 10 and 500 characters")
        String description,
        Long homeTeamId,
        String homeTeamName,
        Long awayTeamId,
        String awayTeamName,
        Long competitionId,
        String competitionName,
        @NotNull(message = "Organizer ID cannot be null")
        Long organizerId,
        String organizerName,
        String organizerLogo,
        String organizerColor,
        @NotNull(message = "Event type ID cannot be null")
        Long eventTypeId,
        String eventTypeName
) {}