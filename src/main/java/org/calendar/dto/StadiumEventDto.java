package org.calendar.dto;

import java.time.LocalDateTime;

public record StadiumEventDto(Long id, Long stadiumId, String eventName, LocalDateTime startTime, LocalDateTime endTime, String description) {}
