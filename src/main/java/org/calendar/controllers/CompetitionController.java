package org.calendar.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.calendar.services.CompetitionService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import java.util.List;
import org.calendar.dto.CompetitionDto;
import org.calendar.mappers.CompetitionConverter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/competitions")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class CompetitionController {

    private final CompetitionService competitionService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @Operation(summary = "Get all competitions", description = "Retrieve a list of all competitions")
    public ResponseEntity<List<CompetitionDto>> getAllCompetitions() {
        List<CompetitionDto> competitions = CompetitionConverter.toDtoList(competitionService.getAllCompetitions());
        return ResponseEntity.status(HttpStatus.OK).body(competitions);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @Operation(summary = "Create a new competition", description = "Create a new competition with the provided details")
    public ResponseEntity<CompetitionDto> createCompetition(@RequestBody CompetitionDto competitionDto) {
        CompetitionDto createdCompetition = CompetitionConverter.toDto(competitionService.createCompetition(CompetitionConverter.toEntity(competitionDto)));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCompetition);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Operation(summary = "Get competition by ID", description = "Retrieve a competition by its ID")
    public ResponseEntity<CompetitionDto> getCompetitionById(@PathVariable Long id) {
        CompetitionDto competition = CompetitionConverter.toDto(competitionService.getCompetitionById(id));
        return ResponseEntity.status(HttpStatus.OK).body(competition);
    }

}
