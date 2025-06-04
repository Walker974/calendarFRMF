package org.calendar.mappers;

import org.calendar.dto.OrganizerDto;
import org.calendar.entities.Organizer;

import java.util.List;
import java.util.stream.Collectors;

public class OrganizerConverter {
    public static OrganizerDto toDto(Organizer organizer) {
        if (organizer == null) {
            return null;
        }
        OrganizerDto dto = new OrganizerDto(
                organizer.getId(),
                organizer.getEmail(),
                organizer.getName(),
                organizer.getPhoneNumber(),
                organizer.getOrganizerType()
        );

        return dto;
    }

    public static Organizer toEntity(OrganizerDto dto) {
        if (dto == null) {
            return null;
        }
        return new Organizer(
                dto.id(),
                dto.email(),
                dto.name(),
                dto.phoneNumber(),
                dto.organizerType()
        );
    }

    public static List<OrganizerDto> toDtoList(List<Organizer> organizers) {
        if (organizers == null || organizers.isEmpty()) {
            return List.of();
        }
        return organizers.stream()
                .map(OrganizerConverter::toDto)
                .collect(Collectors.toList());
    }

    public static List<Organizer> toEntityList(List<OrganizerDto> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return List.of();
        }
        return dtos.stream()
                .map(OrganizerConverter::toEntity)
                .collect(Collectors.toList());
    }
}
