package org.calendar.mappers;

import org.calendar.dto.StadiumDto;
import org.calendar.entities.Stadium;
import org.mapstruct.Mapper;
import org.mapstruct.IterableMapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StadiumMapper {

    @Named("toDto")
    default StadiumDto toDto(Stadium stadium) {
        if (stadium == null) {
            return null;
        }
        return StadiumDto.builder()
                .id(stadium.getId())
                .name(stadium.getName())
                .city(stadium.getCity())
                .build();
    }

    @Named("toEntity")
    default Stadium toEntity(StadiumDto dto) {
        if (dto == null) {
            return null;
        }
        return Stadium.builder()
                .id(dto.id())
                .name(dto.name())
                .city(dto.city())
                .build();
    }

    @IterableMapping(qualifiedByName = "toDto")
    List<StadiumDto> toDtoList(List<Stadium> stadiums);
    @IterableMapping(qualifiedByName = "toEntity")
    List<Stadium> toEntityList(List<StadiumDto> dtos);
}