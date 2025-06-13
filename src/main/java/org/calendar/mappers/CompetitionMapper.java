package org.calendar.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.IterableMapping;
import org.mapstruct.ReportingPolicy;
import org.calendar.dto.CompetitionDto;
import org.calendar.entities.Competition;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CompetitionMapper {

    @Named("toDto")
    default CompetitionDto toDto(Competition competition) {
        if (competition == null) {
            return null;
        }
        return new CompetitionDto(
                competition.getId(),
                competition.getName()
        );
    }

    @Named("toEntity")
    default Competition toEntity(CompetitionDto dto) {
        if (dto == null) {
            return null;
        }
        Competition entity = new Competition();
        entity.setId(dto.id());
        entity.setName(dto.name());
        return entity;
    }

    @IterableMapping(qualifiedByName = "toDto")
    List<CompetitionDto> toDtoList(List<Competition> competitions);

    @IterableMapping(qualifiedByName = "toEntity")
    List<Competition> toEntityList(List<CompetitionDto> dtos);
}