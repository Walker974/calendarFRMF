package org.calendar.mappers;

import org.calendar.dto.StadiumEventDto;
import org.calendar.entities.*;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StadiumEventMapper {
    @Named("toDto")
    default StadiumEventDto toDto(StadiumEvent event) {
        if (event == null) return null;
        StadiumEventDto.StadiumEventDtoBuilder dto = StadiumEventDto.builder()
                .id(event.getId())
                .eventName(event.getEventName())
                .startTime(event.getStartTime())
                .endTime(event.getEndTime())
                .description(event.getDescription());
        if (event.getStadium() != null) {
            dto.stadiumId(event.getStadium().getId())
                    .stadiumName(event.getStadium().getName())
                    .stadiumCity(event.getStadium().getCity() != null
                            ? event.getStadium().getCity().getName() : null);
        }
        if (event.getOrganizer() != null) {
            dto.organizerId(event.getOrganizer().getId())
                    .organizerName(event.getOrganizer().getName())
                    .organizerLogo(event.getOrganizer().getLogo())
                    .organizerColor(event.getOrganizer().getColor());
        }
        if (event instanceof MatchEvent match) {
            if (match.getHomeTeam() != null) {
                dto.homeTeamId(match.getHomeTeam().getId())
                        .homeTeamName(match.getHomeTeam().getName());
            }
            if (match.getAwayTeam() != null) {
                dto.awayTeamId(match.getAwayTeam().getId())
                        .awayTeamName(match.getAwayTeam().getName());
            }
            if (match.getCompetition() != null) {
                dto.competitionId(match.getCompetition().getId())
                        .competitionName(match.getCompetition().getName());
            }
            if (match.getEventType() != null) {
                dto.eventTypeId(match.getEventType().getId())
                        .eventTypeName(match.getEventType().getName());
            }
        } else {
            if (event.getEventType() != null) {
                dto.eventTypeId(event.getEventType().getId())
                        .eventTypeName(event.getEventType().getName());
            }
        }
        return dto.build();
    }

    @Named("toEntity")
    default StadiumEvent toEntity(StadiumEventDto dto) {
        if (dto == null) return null;

        StadiumEvent event;
        boolean isMatch = dto.homeTeamId() != null || dto.awayTeamId() != null;
        if (isMatch) {
            if (dto.homeTeamId() == null || dto.awayTeamId() == null ||
                    dto.eventTypeId() == null || dto.competitionId() == null) {
                throw new IllegalArgumentException("All match event fields (homeTeamId, awayTeamId, eventTypeId, competitionId) must be provided");
            }
            event = MatchEvent.builder()
                    .homeTeam(Team.builder()
                            .id(dto.homeTeamId())
                            .name(dto.homeTeamName())
                            .build())
                    .awayTeam(Team.builder()
                            .id(dto.awayTeamId())
                            .name(dto.awayTeamName())
                            .build())
                    .competition(Competition.builder()
                            .id(dto.competitionId())
                            .name(dto.competitionName())
                            .build())
                    .eventType(EventType.builder()
                            .id(dto.eventTypeId())
                            .name(dto.eventTypeName())
                            .build())
                    .build();
        } else {
            event = StadiumEvent.builder().build();
            if (dto.eventTypeId() != null) {
                event.setEventType(EventType.builder()
                        .id(dto.eventTypeId())
                        .name(dto.eventTypeName())
                        .build());
            }
        }

        event.setId(dto.id());
        event.setEventName(dto.eventName());
        event.setStartTime(dto.startTime());
        event.setEndTime(dto.endTime());
        event.setDescription(dto.description());

        if (dto.stadiumId() != null) {
            event.setStadium(new Stadium(dto.stadiumId(), dto.stadiumName(),
                    dto.stadiumCity() != null ? new City(dto.id(), dto.stadiumCity()) : null));
        }

        if (dto.organizerId() != null) {
            event.setOrganizer(Organizer.builder().id(dto.organizerId()).build());
        }
        return event;
    }

    @IterableMapping(qualifiedByName = "toDto")
    List<StadiumEventDto> toDtoList(List<StadiumEvent> events);

    @IterableMapping(qualifiedByName = "toEntity")
    List<StadiumEvent> toEntityList(List<StadiumEventDto> dtos);
}