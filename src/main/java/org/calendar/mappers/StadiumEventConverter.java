package org.calendar.mappers;

import org.calendar.dto.StadiumEventDto;
import org.calendar.entities.Stadium;
import org.calendar.entities.StadiumEvent;

import java.util.List;
import java.util.stream.Collectors;

public class StadiumEventConverter {
    public static StadiumEventDto toDto(StadiumEvent event) {
        if (event == null) {
            return null;
        }
        return new StadiumEventDto(
                event.getId(),
                event.getStadium(),
                event.getEventName(),
                event.getStartTime(),
                event.getEndTime(),
                event.getDescription(),
                event.getHomeTeam(),
                event.getAwayTeam(),
                event.getOrganizer()
        );
    }

    public static StadiumEvent toEntity(StadiumEventDto dto) {
        if (dto == null) {
            return null;
        }
        Stadium stadium = dto.stadium();
        return StadiumEvent.builder()
                .id(dto.id())
                .stadium(stadium)
                .eventName(dto.eventName())
                .startTime(dto.startTime())
                .endTime(dto.endTime())
                .description(dto.description())
                .homeTeam(dto.homeTeam())
                .awayTeam(dto.awayTeam())
                .organizer(dto.organizer())
                .build();
    }

    public static List<StadiumEventDto> toDtoList(List<StadiumEvent> events) {
        return events.stream().map(StadiumEventConverter::toDto).collect(Collectors.toList());
    }

    public static List<StadiumEvent> toEntityList(List<StadiumEventDto> dtos) {
        return dtos.stream().map(StadiumEventConverter::toEntity).collect(Collectors.toList());
    }
}
