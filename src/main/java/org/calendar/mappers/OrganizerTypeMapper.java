package org.calendar.mappers;

import org.calendar.dto.OrganizerTypeDto;
import org.calendar.entities.OrganizerType;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrganizerTypeMapper {

    @Named("toDto")
    default OrganizerTypeDto toDto(OrganizerType organizerType) {
        if (organizerType == null) {
            return null;
        }
        return OrganizerTypeDto.builder()
                .id(organizerType.getId())
                .name(organizerType.getName())
                .description(organizerType.getDescription())
                .priority(organizerType.getPriority())
                .build();
    }

    @Named("toEntity")
    default OrganizerType toEntity(OrganizerTypeDto dto) {
        if (dto == null) {
            return null;
        }
        return OrganizerType.builder()
                .id(dto.id())
                .name(dto.name())
                .description(dto.description())
                .priority(dto.priority())
                .build();
    }

    @IterableMapping(qualifiedByName = "toDto")
    List<OrganizerTypeDto> toDtoList(List<OrganizerType> organizerTypes);

    @IterableMapping(qualifiedByName = "toEntity")
    List<OrganizerType> toEntityList(List<OrganizerTypeDto> dtos);
}