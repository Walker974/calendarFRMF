package org.calendar.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.calendar.dto.StadiumEventDto;
import org.calendar.mappers.StadiumEventMapper;
import org.calendar.services.StadiumEventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @Operation(summary = "Create a new stadium event", description = "Create a new event for a stadium with the provided details")
    public ResponseEntity<?> createEvent(@RequestBody StadiumEventDto event) {
        try {
            StadiumEventDto newEvent = stadiumEventMapper.toDto(stadiumEventService.createEvent(stadiumEventMapper.toEntity(event)));
            return ResponseEntity.status(HttpStatus.CREATED).body(newEvent);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/{stadiumId}", method = RequestMethod.GET)
    @Operation(summary = "Get events by stadium ID", description = "Retrieve a list of events for a specific stadium")
    public ResponseEntity<List<StadiumEventDto>> getEventsByStadiumId(@PathVariable Long stadiumId) {
        List<StadiumEventDto> events = stadiumEventMapper.toDtoList(stadiumEventService.getEventsByStadiumId(stadiumId));
        return ResponseEntity.status(HttpStatus.OK).body(events);
    }

}
