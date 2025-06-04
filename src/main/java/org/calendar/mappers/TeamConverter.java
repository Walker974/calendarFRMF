package org.calendar.mappers;

import org.calendar.dto.TeamDto;
import org.calendar.entities.Team;
import java.util.List;
import java.util.stream.Collectors;

public class TeamConverter {

    public static TeamDto toDto(Team team) {
        if (team == null) return null;
        return new TeamDto(
                team.getId(),
                team.getName()
        );
    }

    public static Team toEntity(TeamDto dto) {
        if (dto == null) return null;
        return new Team(
                dto.id(),
                dto.name()
        );
    }

    public static List<TeamDto> toDtoList(List<Team> teams) {
        return teams.stream()
                .map(TeamConverter::toDto)
                .collect(Collectors.toList());
    }

    public static List<Team> toEntityList(List<TeamDto> dtos) {
        return dtos.stream()
                .map(TeamConverter::toEntity)
                .collect(Collectors.toList());
    }
}
