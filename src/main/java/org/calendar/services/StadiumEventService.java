package org.calendar.services;

import lombok.RequiredArgsConstructor;
import org.calendar.dao.StadiumEventRepository;
import org.calendar.entities.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StadiumEventService {
    private final StadiumEventRepository stadiumEventRepository;
    private final EventTypeService eventTypeService;
    private final TeamService teamService;
    private final OrganizerService organizerService;
    private final StadiumService stadiumService;

    public StadiumEvent createEvent(StadiumEvent event) {
        if (event.getStadium() == null || event.getStadium().getId() == null) {
            throw new IllegalArgumentException("Stadium ID must be provided");
        }
        Stadium persistentStadium = stadiumService.getStadiumById(event.getStadium().getId());
        if (persistentStadium == null) {
            throw new IllegalArgumentException("Stadium not found");
        }
        event.setStadium(persistentStadium);
        if (event.getOrganizer() == null || event.getOrganizer().getId() == null) {
            throw new IllegalArgumentException("Organizer ID must be provided");
        }
        Organizer persistentOrganizer = organizerService.getOrganizerById(event.getOrganizer().getId());
        if (persistentOrganizer == null) {
            throw new IllegalArgumentException("Organizer not found");
        }
        event.setOrganizer(persistentOrganizer);
        List<StadiumEvent> events = stadiumEventRepository.findByStadiumId(persistentStadium.getId());
        for (StadiumEvent existing : events) {
            if (event.getStartTime().isBefore(existing.getEndTime())
                    && event.getEndTime().isAfter(existing.getStartTime())) {
                throw new IllegalArgumentException("Stadium is already booked for the selected times");
            }
        }

        if (event instanceof MatchEvent matchEvent) {
            if (matchEvent.getHomeTeam() != null && matchEvent.getHomeTeam().getId() != null) {
                Team persistentHomeTeam = teamService.getTeamById(matchEvent.getHomeTeam().getId());
                if (persistentHomeTeam == null) {
                    throw new IllegalArgumentException("Home team not found");
                }
                matchEvent.setHomeTeam(persistentHomeTeam);
            }
            if (matchEvent.getAwayTeam() != null && matchEvent.getAwayTeam().getId() != null) {
                Team persistentAwayTeam = teamService.getTeamById(matchEvent.getAwayTeam().getId());
                if (persistentAwayTeam == null) {
                    throw new IllegalArgumentException("Away team not found");
                }
                matchEvent.setAwayTeam(persistentAwayTeam);
            }
            if (matchEvent.getEventType() != null && matchEvent.getEventType().getId() != null) {
                EventType persistentEventType = eventTypeService.getEventTypeById(matchEvent.getEventType().getId());
                if (persistentEventType == null) {
                    throw new IllegalArgumentException("Event type not found");
                }
                matchEvent.setEventType(persistentEventType);
            }
            if (matchEvent.getCompetition() != null && matchEvent.getCompetition().getId() != null) {
                Competition persistentCompetition = matchEvent.getCompetition();
                if (persistentCompetition.getId() == null) {
                    throw new IllegalArgumentException("Competition ID must be provided");
                }
                matchEvent.setCompetition(persistentCompetition);
            }
        }
        return stadiumEventRepository.save(event);
    }

    public List<StadiumEvent> getAllEvents() {
        return stadiumEventRepository.findAll();
    }

    public List<StadiumEvent> getEventsByStadiumId(Long stadiumId) {
        return stadiumEventRepository.findByStadiumId(stadiumId);
    }
}