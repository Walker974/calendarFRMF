package org.calendar.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.calendar.dto.CityDto;
import org.calendar.mappers.CityConverter;
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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @Operation(summary = "Get all cities", description = "Retrieve a list of all cities")
    public ResponseEntity<List<CityDto>> getAllCities() {
        List<CityDto> cities = CityConverter.toDtoList(cityService.getAllCities());
        if (cities.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(List.of());
        }
        return ResponseEntity.status(HttpStatus.OK).body(cities);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @Operation(summary = "Create a new city", description = "Create a new city with the provided details")
    public ResponseEntity<CityDto> createCity(@RequestBody CityDto cityDto) {
        CityDto createdCity = CityConverter.toDto(cityService.createCity(CityConverter.toEntity(cityDto)));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCity);
    }
}
