package org.calendar.controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.calendar.dto.CityDto;
import org.calendar.mappers.CityMapper;
import org.calendar.services.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class CityController {

    private final CityService cityService;
    private final CityMapper cityMapper;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @Operation(summary = "Get all cities", description = "Retrieve a list of all cities")
    public ResponseEntity<List<CityDto>> getAllCities() {
        List<CityDto> cities = cityMapper.toDtoList(cityService.getAllCities());
        return ResponseEntity.status(HttpStatus.OK).body(cities);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @Operation(summary = "Create a new city", description = "Create a new city with the provided details")
    public ResponseEntity<CityDto> createCity(@Valid @RequestBody CityDto cityDto) {
        CityDto createdCity = cityMapper.toDto(cityService.createCity(cityMapper.toEntity(cityDto)));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCity);
    }

    @RequestMapping(value = "/{cityId}", method = RequestMethod.PUT)
    @Operation(summary = "Update a city", description = "Update the details of an existing city")
    public ResponseEntity<CityDto> updateCity(@PathVariable Long cityId, @Valid @RequestBody CityDto cityDto) {
        try {
            CityDto updatedCity = cityMapper.toDto(cityService.updateCity(cityId, cityMapper.toEntity(cityDto)));
            return ResponseEntity.status(HttpStatus.OK).body(updatedCity);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @RequestMapping(value = "/{cityId}", method = RequestMethod.DELETE)
    @Operation(summary = "Delete a city", description = "Delete a city by its ID")
    public ResponseEntity<String> deleteCity(@PathVariable Long cityId) {
        try {
            cityService.deleteCity(cityId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
