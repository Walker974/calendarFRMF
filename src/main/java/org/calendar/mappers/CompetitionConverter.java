package org.calendar.mappers;

import org.calendar.entities.Competition;
import org.calendar.dto.CompetitionDto;

import java.util.List;
import java.util.stream.Collectors;

public class CompetitionConverter {

    public static CompetitionDto toDto(Competition competition) {
        if (competition == null) {
            return null;
        }
        return new CompetitionDto(competition.getId(), competition.getName());
    }

    public static Competition toEntity(CompetitionDto dto) {
        if (dto == null) {
            return null;
        }
        Competition competition = new Competition();
        competition.setId(dto.id());
        competition.setName(dto.name());
        return competition;
    }

    public static List<CompetitionDto> toDtoList(List<Competition> competitions) {
        return competitions.stream()
                .map(CompetitionConverter::toDto)
                .collect(Collectors.toList());
    }

    public static List<Competition> toEntityList(List<CompetitionDto> dtos) {
        return dtos.stream()
                .map(CompetitionConverter::toEntity)
                .collect(Collectors.toList());
    }
}