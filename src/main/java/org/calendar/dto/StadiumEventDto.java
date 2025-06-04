package org.calendar.dto;

import org.calendar.entities.Stadium;
import org.calendar.entities.Team;
import java.time.LocalDateTime;

public record StadiumEventDto(Long id, Stadium stadium, String eventName, LocalDateTime startTime, LocalDateTime endTime, String description, Team homeTeam, Team awayTeam) {}
