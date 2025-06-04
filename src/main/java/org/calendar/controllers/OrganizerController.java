package org.calendar.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.calendar.dto.OrganizerDto;
import org.calendar.entities.Organizer;
import org.calendar.mappers.OrganizerConverter;
import org.calendar.services.OrganizerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
        List<OrganizerDto> organizers = OrganizerConverter.toDtoList(organizerService.getAllOrganizers());
        if (organizers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(organizers);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Operation(summary = "Get organizer by ID", description = "Retrieve an organizer by its ID")
    public ResponseEntity<OrganizerDto> getOrganizerById(@PathVariable Long id) {
        Organizer organizer = organizerService.getOrganizerById(id);
        if (organizer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(OrganizerConverter.toDto(organizer));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @Operation(summary = "Create a new organizer", description = "Add a new organizer to the system")
    public ResponseEntity<OrganizerDto> createOrganizer(OrganizerDto organizerDto) {
        Organizer organizer = OrganizerConverter.toEntity(organizerDto);
        Organizer createdOrganizer = organizerService.createOrganizer(organizer);
        return ResponseEntity.status(HttpStatus.CREATED).body(OrganizerConverter.toDto(createdOrganizer));
    }
}
