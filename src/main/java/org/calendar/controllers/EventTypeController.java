package org.calendar.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.calendar.dto.EventTypeDto;
import org.calendar.mappers.EventTypeConverter;
import org.calendar.services.EventTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/event-types")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class EventTypeController {

    private final EventTypeService eventTypeService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @Operation(summary = "Get all event types", description = "Retrieve a list of all event types")
    public ResponseEntity<List<EventTypeDto>> getAllEventTypes() {
        List<EventTypeDto> eventTypes = EventTypeConverter.toDtoList(eventTypeService.getAllEventTypes());
        return ResponseEntity.status(HttpStatus.OK).body(eventTypes);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @Operation(summary = "Create a new event type", description = "Create a new event type with the provided details")
    public ResponseEntity<EventTypeDto> createEventType(@RequestBody EventTypeDto eventTypeDto) {
        EventTypeDto createdEventType = EventTypeConverter.toDto(eventTypeService.createEventType(EventTypeConverter.toEntity(eventTypeDto)));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEventType);
    }
}
