package org.calendar.mappers;

import org.calendar.dto.EventTypeDto;
import org.calendar.entities.EventType;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.IterableMapping;
import org.mapstruct.ReportingPolicy;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventTypeMapper {

    @Named("toDto")
    default EventTypeDto toDto(EventType eventType) {
        if (eventType == null) {
            return null;
        }
        return new EventTypeDto(
                eventType.getId(),
                eventType.getName(),
                eventType.getDescription()
        );
    }

    @Named("toEntity")
    default EventType toEntity(EventTypeDto dto) {
        if (dto == null) {
            return null;
        }
        EventType entity = new EventType();
        entity.setId(dto.id());
        entity.setName(dto.name());
        entity.setDescription(dto.description());
        return entity;
    }

    @IterableMapping(qualifiedByName = "toDto")
    List<EventTypeDto> toDtoList(List<EventType> eventTypes);

    @IterableMapping(qualifiedByName = "toEntity")
    List<EventType> toEntityList(List<EventTypeDto> dtos);
}