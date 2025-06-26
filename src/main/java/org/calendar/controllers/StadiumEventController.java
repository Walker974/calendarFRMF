package org.calendar.controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.calendar.dto.CancelRequest;
import org.calendar.dto.StadiumEventDto;
import org.calendar.mappers.StadiumEventMapper;
import org.calendar.services.StadiumEventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import org.calendar.entities.StadiumEvent;
import java.time.OffsetDateTime;

import java.util.List;

@RestController
@RequestMapping("/api/stadium-events")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class StadiumEventController {

    private final StadiumEventService stadiumEventService;
    private final StadiumEventMapper stadiumEventMapper;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @Operation(summary = "Get all stadium events", description = "Retrieve a list of all stadium events")
    public ResponseEntity<List<StadiumEventDto>> getAllStadiumEvents() {
        List<StadiumEventDto> events = stadiumEventMapper.toDtoList(stadiumEventService.getAllEvents());
        return ResponseEntity.status(HttpStatus.OK).body(events);
    }

    @PostMapping("/{userId}")
    @Operation(summary = "Create a new stadium event", description = "Create a new event for a stadium with the provided details")
    public ResponseEntity<?> createEvent(@PathVariable Long userId, @Valid @RequestBody StadiumEventDto event) {
        try {
            StadiumEventDto newEvent = stadiumEventMapper.toDto(stadiumEventService.createEvent(stadiumEventMapper.toEntity(event), userId));
            return ResponseEntity.status(HttpStatus.OK).body(newEvent);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{stadiumId}")
    @Operation(summary = "Get events by stadium ID", description = "Retrieve a list of events for a specific stadium")
    public ResponseEntity<List<StadiumEventDto>> getEventsByStadiumId(@PathVariable Long stadiumId) {
        List<StadiumEventDto> events = stadiumEventMapper.toDtoList(stadiumEventService.getEventsByStadiumId(stadiumId));
        return ResponseEntity.status(HttpStatus.OK).body(events);
    }

    @GetMapping("/by-range")
    public ResponseEntity<List<StadiumEventDto>> getEventsByRange(
            @RequestParam("start") String start,
            @RequestParam("end") String end
    ) {
        OffsetDateTime odStart = OffsetDateTime.parse(start);
        OffsetDateTime odEnd = OffsetDateTime.parse(end);
        LocalDateTime s = odStart.toLocalDateTime();
        LocalDateTime e = odEnd.toLocalDateTime();
        List<StadiumEvent> events = stadiumEventService.getEventsBetween(s, e);
        return ResponseEntity.ok(stadiumEventMapper.toDtoList(events));
    }

    @PutMapping("/cancel/{eventId}")
    @Operation(summary = "Cancel an event", description = "Cancel an existing stadium event by its ID")
    public ResponseEntity<?> cancelEvent(@RequestParam("userId") Long userId, @PathVariable Long eventId, @RequestBody CancelRequest cancelRequest) {
        try {
            String reason = cancelRequest.reason();
            StadiumEventDto canceledEvent = stadiumEventMapper.toDto(stadiumEventService.cancelEvent(userId, eventId, reason));
            return ResponseEntity.status(HttpStatus.OK).body(canceledEvent);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
