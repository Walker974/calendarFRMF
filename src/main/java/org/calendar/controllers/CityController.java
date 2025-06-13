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
}
