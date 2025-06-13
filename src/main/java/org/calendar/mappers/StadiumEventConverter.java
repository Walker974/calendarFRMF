package org.calendar.mappers;

import org.calendar.dto.StadiumEventDto;
import org.calendar.entities.StadiumEvent;
import org.calendar.entities.MatchEvent;
import org.calendar.entities.Stadium;
import org.calendar.entities.Organizer;
import org.calendar.entities.Team;
import org.calendar.entities.EventType;
import org.calendar.entities.Competition;

public class StadiumEventConverter {

    public static StadiumEventDto toDto(StadiumEvent event) {
        if (event == null) {
            return null;
        }
        Long homeTeamId = null;
        String homeTeamName = null;
        Long awayTeamId = null;
        String awayTeamName = null;
        Long competitionId = null;
        String competitionName = null;
        Long eventTypeId = null;
        String eventTypeName = null;
        if (event instanceof MatchEvent matchEvent) {
            if (matchEvent.getHomeTeam() != null) {
                homeTeamId = matchEvent.getHomeTeam().getId();
                homeTeamName = matchEvent.getHomeTeam().getName();
            }
            if (matchEvent.getAwayTeam() != null) {
                awayTeamId = matchEvent.getAwayTeam().getId();
                awayTeamName = matchEvent.getAwayTeam().getName();
            }
            if (matchEvent.getCompetition() != null) {
                competitionId = matchEvent.getCompetition().getId();
                competitionName = matchEvent.getCompetition().getName();
            }
            if (matchEvent.getEventType() != null) {
                eventTypeId = matchEvent.getEventType().getId();
                eventTypeName = matchEvent.getEventType().getName();
            }
        } else { // StadiumEvent branch
            if (event.getEventType() != null) {
                eventTypeId = event.getEventType().getId();
                eventTypeName = event.getEventType().getName();
            }
        }
        Stadium stadium = event.getStadium();
        Organizer organizer = event.getOrganizer();
        String stadiumName = stadium != null ? stadium.getName() : null;
        String stadiumCity = (stadium != null && stadium.getCity() != null)
                ? stadium.getCity().getName() : null;
        String organizerName = organizer != null ? organizer.getName() : null;
        String organizerLogo = organizer != null ? organizer.getLogo() : null;
        String organizerColor = organizer != null ? organizer.getColor() : null;
        return new StadiumEventDto(
                event.getId(),
                stadium != null ? stadium.getId() : null,
                stadiumName,
                stadiumCity,
                event.getEventName(),
                event.getStartTime(),
                event.getEndTime(),
                event.getDescription(),
                homeTeamId,
                homeTeamName,
                awayTeamId,
                awayTeamName,
                competitionId,
                competitionName,
                organizer != null ? organizer.getId() : null,
                organizerName,
                organizerLogo,
                organizerColor,
                eventTypeId,
                eventTypeName
        );
    }

    public static StadiumEvent toEntity(StadiumEventDto dto) {
        StadiumEvent event;
        boolean anyMatchFieldProvided = dto.homeTeamId() != null || dto.awayTeamId() != null;
        boolean allMatchFieldsProvided = dto.homeTeamId() != null && dto.awayTeamId() != null;
        if (anyMatchFieldProvided) {
            if (!allMatchFieldsProvided || dto.eventTypeId() == null || dto.competitionId() == null) {
                throw new IllegalArgumentException("All match event fields (homeTeamId, awayTeamId, eventTypeId, competitionId) must be provided");
            }
            MatchEvent matchEvent = new MatchEvent();
            Team homeTeam = new Team();
            homeTeam.setId(dto.homeTeamId());
            matchEvent.setHomeTeam(homeTeam);
            Team awayTeam = new Team();
            awayTeam.setId(dto.awayTeamId());
            matchEvent.setAwayTeam(awayTeam);
            EventType eventType = new EventType();
            eventType.setId(dto.eventTypeId());
            matchEvent.setEventType(eventType);
            Competition competition = new Competition();
            competition.setId(dto.competitionId());
            matchEvent.setCompetition(competition);
            event = matchEvent;
        } else {
            event = new StadiumEvent();
            if (dto.eventTypeId() != null) {
                EventType eventType = new EventType();
                eventType.setId(dto.eventTypeId());
                event.setEventType(eventType);
            }
        }
        event.setId(dto.id());
        event.setEventName(dto.eventName());
        event.setStartTime(dto.startTime());
        event.setEndTime(dto.endTime());
        event.setDescription(dto.description());
        if (dto.stadiumId() != null) {
            Stadium stadium = new Stadium();
            stadium.setId(dto.stadiumId());
            event.setStadium(stadium);
        }
        if (dto.organizerId() != null) {
            Organizer organizer = new Organizer();
            organizer.setId(dto.organizerId());
            event.setOrganizer(organizer);
        }
        return event;
    }

    public static java.util.List<StadiumEventDto> toDtoList(java.util.List<StadiumEvent> events) {
        return events.stream().map(StadiumEventConverter::toDto).collect(java.util.stream.Collectors.toList());
    }

    public static java.util.List<StadiumEvent> toEntityList(java.util.List<StadiumEventDto> dtos) {
        return dtos.stream().map(StadiumEventConverter::toEntity).collect(java.util.stream.Collectors.toList());
    }
}