package org.calendar.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.calendar.dto.OrganizerDto;
import org.calendar.entities.Organizer;
import org.calendar.mappers.OrganizerConverter;
import org.calendar.services.OrganizerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/organizers")
@RequiredArgsConstructor
public class OrganizerController {

    private final OrganizerService organizerService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @Operation(summary = "Get all organizers", description = "Retrieve a list of all organizers")
    public ResponseEntity<List<OrganizerDto>> getAllOrganizers() {
        // TODO: Check here not finished
        List<OrganizerDto> organizers = OrganizerConverter.toDtoList(organizerService.getAllOrganizers());
        if (organizers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(organizers);
    }
}
