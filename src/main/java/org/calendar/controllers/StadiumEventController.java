package org.calendar.controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import org.calendar.dto.StadiumEventDto;
import org.calendar.entities.StadiumEvent;
import org.calendar.mappers.StadiumEventConverter;
import org.calendar.services.StadiumEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/stadium-events")
@RequiredArgsConstructor
public class StadiumEventController {

    private final StadiumEventService stadiumEventService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @Operation(summary = "Get all stadium events", description = "Retrieve a list of all stadium events")
    public List<StadiumEventDto> getAllEvents() {
        return StadiumEventConverter.toDtoList(stadiumEventService.getAllEvents());
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @Operation(summary = "Create a new stadium event", description = "Create a new event for a stadium with the provided details")
    public ResponseEntity<?> createEvent(@RequestBody StadiumEventDto event) {
        try {
            StadiumEventDto newEvent = StadiumEventConverter.toDto(stadiumEventService.createEvent(StadiumEventConverter.toEntity(event)));
            return ResponseEntity.status(HttpStatus.CREATED).body(newEvent);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
