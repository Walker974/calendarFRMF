package org.calendar.controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.calendar.dto.OrganizerTypeDto;
import org.calendar.mappers.OrganizerTypeMapper;
import org.calendar.services.OrganizerTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organizer-types")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class OrganizerTypeController {

    private final OrganizerTypeService organizerTypeService;
    private final OrganizerTypeMapper organizerTypeMapper;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @Operation(summary = "Get all organizer types", description = "Retrieve a list of all organizer types")
    public ResponseEntity<List<OrganizerTypeDto>> getAllOrganizerTypes() {
        List<OrganizerTypeDto> organizerTypes = organizerTypeMapper.toDtoList(organizerTypeService.getAllOrganizerTypes());
        return ResponseEntity.status(HttpStatus.OK).body(organizerTypes);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Operation(summary = "Get organizer type by ID", description = "Retrieve an organizer type by its ID")
    public ResponseEntity<OrganizerTypeDto> getOrganizerTypeById(@PathVariable Long id) {
        OrganizerTypeDto organizerType = organizerTypeMapper.toDto(organizerTypeService.getOrganizerTypeById(id));
        return ResponseEntity.status(HttpStatus.OK).body(organizerType);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @Operation(summary = "Create a new organizer type", description = "Add a new organizer type to the system")
    public ResponseEntity<OrganizerTypeDto> createOrganizerType(@Valid @RequestBody OrganizerTypeDto organizerTypeDto) {
        OrganizerTypeDto createdOrganizerType = organizerTypeMapper.toDto(organizerTypeService.createOrganizerType(organizerTypeMapper.toEntity(organizerTypeDto)));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrganizerType);
    }
}
