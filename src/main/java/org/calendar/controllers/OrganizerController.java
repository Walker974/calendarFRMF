package org.calendar.controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.calendar.dto.OrganizerDto;
import org.calendar.entities.Organizer;
import org.calendar.mappers.OrganizerMapper;
import org.calendar.services.OrganizerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organizers")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class OrganizerController {

    private final OrganizerService organizerService;
    private final OrganizerMapper organizerMapper;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @Operation(summary = "Get all organizers", description = "Retrieve a list of all organizers")
    public ResponseEntity<List<OrganizerDto>> getAllOrganizers() {
        List<OrganizerDto> organizers = organizerMapper.toDtoList(organizerService.getAllOrganizers());
        return ResponseEntity.status(HttpStatus.OK).body(organizers);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Operation(summary = "Get organizer by ID", description = "Retrieve an organizer by its ID")
    public ResponseEntity<OrganizerDto> getOrganizerById(@PathVariable Long id) {
        Organizer organizer = organizerService.getOrganizerById(id);
        return ResponseEntity.status(HttpStatus.OK).body(organizerMapper.toDto(organizer));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @Operation(summary = "Create a new organizer", description = "Add a new organizer to the system")
    public ResponseEntity<OrganizerDto> createOrganizer(@Valid @RequestBody OrganizerDto organizerDto) {
        Organizer organizer = organizerMapper.toEntity(organizerDto);
        Organizer createdOrganizer = organizerService.createOrganizer(organizer);
        return ResponseEntity.status(HttpStatus.CREATED).body(organizerMapper.toDto(createdOrganizer));
    }
}
