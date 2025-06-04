package org.calendar.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.calendar.dto.OrganizerTypeDto;
import org.calendar.mappers.OrganizerTypeConverter;
import org.calendar.services.OrganizerTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/organizer-types")
@RequiredArgsConstructor
public class OrganizerTypeController {

    private final OrganizerTypeService organizerTypeService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @Operation(summary = "Get all organizer types", description = "Retrieve a list of all organizer types")
    public ResponseEntity<List<OrganizerTypeDto>> getAllOrganizerTypes() {
        List<OrganizerTypeDto> organizerTypes = OrganizerTypeConverter.toDtoList(organizerTypeService.getAllOrganizerTypes());
        if (organizerTypes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(organizerTypes);
        }
        return ResponseEntity.status(HttpStatus.OK).body(organizerTypes);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Operation(summary = "Get organizer type by ID", description = "Retrieve an organizer type by its ID")
    public ResponseEntity<OrganizerTypeDto> getOrganizerTypeById(@PathVariable Long id) {
        OrganizerTypeDto organizerType = OrganizerTypeConverter.toDto(organizerTypeService.getOrganizerTypeById(id));
        if (organizerType == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(organizerType);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @Operation(summary = "Create a new organizer type", description = "Add a new organizer type to the system")
    public ResponseEntity<OrganizerTypeDto> createOrganizerType(OrganizerTypeDto organizerTypeDto) {
        OrganizerTypeDto createdOrganizerType = OrganizerTypeConverter.toDto(organizerTypeService.createOrganizerType(OrganizerTypeConverter.toEntity(organizerTypeDto)));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrganizerType);
    }
}
