package org.calendar.mappers;

import org.calendar.dto.StadiumDto;
import org.calendar.entities.Stadium;
import java.util.List;
import java.util.stream.Collectors;

public class StadiumConverter {
    public static StadiumDto toDto(Stadium stadium) {
        if (stadium == null) {
            return null;
        }
        return new StadiumDto(
                stadium.getId(),
                stadium.getName(),
                stadium.getCity()
        );
    }

    public static Stadium toEntity(StadiumDto dto) {
        if (dto == null) {
            return null;
        }
        Stadium stadium = new Stadium();
        stadium.setId(dto.id());
        stadium.setName(dto.name());
        stadium.setCity(dto.city());
        return stadium;
    }

    public static List<StadiumDto> toDtoList(List<Stadium> stadiums) {
        if (stadiums == null || stadiums.isEmpty()) {
            return List.of();
        }
        return stadiums.stream()
                .map(StadiumConverter::toDto)
                .collect(Collectors.toList());
    }

    public static List<Stadium> toEntityList(List<StadiumDto> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return List.of();
        }
        return dtos.stream()
                .map(StadiumConverter::toEntity)
                .collect(Collectors.toList());
    }
}
