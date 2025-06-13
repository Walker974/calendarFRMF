package org.calendar.controllers;

import lombok.RequiredArgsConstructor;
import org.calendar.services.TeamService;
import org.calendar.dto.TeamDto;
import org.calendar.mappers.TeamMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;

@RestController
@RequestMapping("/api/teams")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class TeamController {

    private final TeamService teamService;
    private final TeamMapper teamMapper;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @Operation(summary = "Get all teams", description = "Retrieve a list of all teams")
    public ResponseEntity<List<TeamDto>> getAllTeams() {
        List<TeamDto> teams = teamMapper.toDtoList(teamService.getAllTeams());
        return ResponseEntity.status(HttpStatus.OK).body(teams);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @Operation(summary = "Create a new team", description = "Create a new team with the provided details")
    public TeamDto createTeam(@RequestBody TeamDto teamDto) {
        return teamMapper.toDto(teamService.createTeam(teamMapper.toEntity(teamDto)));
    }

}
