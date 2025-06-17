package org.calendar.controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.calendar.dto.StadiumDto;
import org.calendar.mappers.StadiumMapper;
import org.calendar.services.StadiumService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stadiums")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class StadiumController {
    private final StadiumService stadiumService;
    private final StadiumMapper stadiumMapper;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @Operation(summary = "Get all stadiums", description = "Retrieve a list of all stadiums")
    public ResponseEntity<List<StadiumDto>> getAllStadiums() {
        List<StadiumDto> stadiums = stadiumMapper.toDtoList(stadiumService.getAllStadiums());
        return ResponseEntity.status(HttpStatus.OK).body(stadiums);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @Operation(summary = "Create a new stadium", description = "Create a new stadium with the provided details")
    public ResponseEntity<?> createStadium(@Valid @RequestBody StadiumDto stadium) {
        StadiumDto created = stadiumMapper.toDto(stadiumService.createStadium(stadiumMapper.toEntity(stadium)));
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @RequestMapping(value = "/{stadiumId}", method = RequestMethod.PUT)
    @Operation(summary = "Update a stadium", description = "Update the details of an existing stadium")
    public ResponseEntity<?> updateStadium(@PathVariable Long stadiumId, @Valid @RequestBody StadiumDto stadium) {
        try {
            StadiumDto updated = stadiumMapper.toDto(stadiumService.updateStadium(stadiumId, stadiumMapper.toEntity(stadium)));
            return ResponseEntity.status(HttpStatus.OK).body(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/{stadiumId}", method = RequestMethod.DELETE)
    @Operation(summary = "Delete a stadium", description = "Delete a stadium by its ID")
    public ResponseEntity<?> deleteStadium(@PathVariable Long stadiumId) {
        try {
            stadiumService.deleteStadium(stadiumId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
