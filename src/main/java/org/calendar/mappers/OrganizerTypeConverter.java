package org.calendar.mappers;

import org.calendar.dto.OrganizerTypeDto;
import org.calendar.entities.OrganizerType;

import java.util.List;
import java.util.stream.Collectors;

public class OrganizerTypeConverter {
    public static OrganizerTypeDto toDto(OrganizerType organizerType) {
        if (organizerType == null) {
            return null;
        }
        return new OrganizerTypeDto(
                organizerType.getId(),
                organizerType.getName(),
                organizerType.getDescription(),
                organizerType.getPriority()
        );
    }

    public static OrganizerType toEntity(OrganizerTypeDto dto) {
        if (dto == null) {
            return null;
        }
        OrganizerType organizerType = new OrganizerType();
        organizerType.setId(dto.id());
        organizerType.setName(dto.name());
        organizerType.setDescription(dto.description());
        organizerType.setPriority(dto.priority());
        return organizerType;
    }

    public static List<OrganizerTypeDto> toDtoList(List<OrganizerType> organizerTypes) {
        if (organizerTypes == null || organizerTypes.isEmpty()) {
            return List.of();
        }
        return organizerTypes.stream()
                .map(OrganizerTypeConverter::toDto)
                .collect(Collectors.toList());
    }

    public static List<OrganizerType> toEntityList(List<OrganizerTypeDto> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return List.of();
        }
        return dtos.stream()
                .map(OrganizerTypeConverter::toEntity)
                .collect(Collectors.toList());
    }
}
