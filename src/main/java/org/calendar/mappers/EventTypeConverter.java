package org.calendar.mappers;

import org.calendar.dto.EventTypeDto;
import org.calendar.entities.EventType;
import java.util.List;
import java.util.stream.Collectors;

public class EventTypeConverter {

    public static EventTypeDto toDto(EventType eventType) {
        if (eventType == null) {
            return null;
        }
        return new EventTypeDto(
                eventType.getId(),
                eventType.getName(),
                eventType.getDescription()
        );
    }

    public static EventType toEntity(EventTypeDto dto) {
        if (dto == null) {
            return null;
        }
        EventType eventType = new EventType();
        eventType.setId(dto.id());
        eventType.setName(dto.name());
        eventType.setDescription(dto.description());
        return eventType;
    }

    public static List<EventTypeDto> toDtoList(List<EventType> eventTypes) {
        return eventTypes.stream()
                .map(EventTypeConverter::toDto)
                .collect(Collectors.toList());
    }

    public static List<EventType> toEntityList(List<EventTypeDto> dtos) {
        return dtos.stream()
                .map(EventTypeConverter::toEntity)
                .collect(Collectors.toList());
    }
}