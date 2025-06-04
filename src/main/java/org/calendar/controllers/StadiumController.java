package org.calendar.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.calendar.dto.StadiumDto;
import org.calendar.mappers.StadiumConverter;
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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @Operation(summary = "Get all stadiums", description = "Retrieve a list of all stadiums")
    public ResponseEntity<List<StadiumDto>> getAllStadiums() {
        List<StadiumDto> stadiums = StadiumConverter.toDtoList(stadiumService.getAllStadiums());
        return ResponseEntity.status(HttpStatus.OK).body(stadiums);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @Operation(summary = "Create a new stadium", description = "Create a new stadium with the provided details")
    public ResponseEntity<?> createStadium(@RequestBody StadiumDto stadium) {
        StadiumDto created = StadiumConverter.toDto(stadiumService.createStadium(StadiumConverter.toEntity(stadium)));
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}
