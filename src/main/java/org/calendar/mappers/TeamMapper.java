package org.calendar.mappers;

import org.calendar.dto.TeamDto;
import org.calendar.entities.Team;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TeamMapper {

    @Named("toDto")
    default TeamDto toDto(Team team) {
        if (team == null) {
            return null;
        }
        return TeamDto.builder()
                .id(team.getId())
                .name(team.getName())
                .build();
    }

    @Named("toEntity")
    default Team toEntity(TeamDto dto) {
        if (dto == null) {
            return null;
        }
        return Team.builder()
                .id(dto.id())
                .name(dto.name())
                .build();
    }

    @IterableMapping(qualifiedByName = "toDto")
    List<TeamDto> toDtoList(List<Team> teams);

    @IterableMapping(qualifiedByName = "toEntity")
    List<Team> toEntityList(List<TeamDto> dtos);
}