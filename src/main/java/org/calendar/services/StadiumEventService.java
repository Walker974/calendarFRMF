package org.calendar.services;

import lombok.RequiredArgsConstructor;
import org.calendar.dao.StadiumEventRepository;
import org.calendar.entities.*;
import org.calendar.enums.ActionType;
import org.calendar.enums.Role;
import org.calendar.enums.StadiumEventStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StadiumEventService {
    private final StadiumEventRepository stadiumEventRepository;
    private final EventTypeService eventTypeService;
    private final TeamService teamService;
    private final OrganizerService organizerService;
    private final StadiumService stadiumService;
    private final ActionLogService actionLogService;
    private final AuthentificationService authentificationService;

    @PreAuthorize("hasRole('SUPERADMIN') or hasRole('EDITOR')")
    public StadiumEvent createEvent(StadiumEvent event, Long userId) {
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
        User currentUser = authentificationService.getUserById(userId);
        if (currentUser == null) {
            throw new IllegalArgumentException("User not found");
        }
        event.setCreatedBy(currentUser);
        event.setStatus(checkCurrentStatus(event));

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
                matchEvent.setCompetition(persistentCompetition);
            }
        }
        event.setTimeCreated(LocalDateTime.now());
        event.setTimeUpdated(LocalDateTime.now());
        actionLogService.log(
                ActionType.CREATE_EVENT,
                currentUser
        );
        return stadiumEventRepository.save(event);
    }

    private StadiumEventStatus checkCurrentStatus(StadiumEvent event) {
        LocalDateTime now = LocalDateTime.now();
        if (event.getStartTime().isAfter(now)) {
            return StadiumEventStatus.UPCOMING;
        } else if (event.getEndTime().isBefore(now)) {
            return StadiumEventStatus.PAST;
        } else {
            return StadiumEventStatus.IN_PROGRESS;
        }
    }

    public List<StadiumEvent> getAllEvents() {
        List<StadiumEvent> events = stadiumEventRepository.findAll();
        events.forEach(event -> {
            if (event.getStatus() == StadiumEventStatus.UPCOMING || event.getStatus() == StadiumEventStatus.IN_PROGRESS) {
                event.setStatus(checkCurrentStatus(event));
                stadiumEventRepository.save(event);
            }
        });
        return events;
    }

    public List<StadiumEvent> getEventsByStadiumId(Long stadiumId) {
        return stadiumEventRepository.findByStadiumId(stadiumId);
    }

    @PreAuthorize("hasRole('SUPERADMIN') or hasRole('EDITOR')")
    public List<StadiumEvent> getEventsBetween(LocalDateTime start, LocalDateTime end) {
        List<StadiumEvent> result = stadiumEventRepository.findByStartTimeBetween(start, end);
        for (StadiumEvent event : result) {
            if (event.getStatus() == StadiumEventStatus.UPCOMING
                    || event.getStatus() == StadiumEventStatus.IN_PROGRESS) {
                event.setStatus(checkCurrentStatus(event));
                stadiumEventRepository.save(event);
            }
        }
        result.removeIf(event -> event.getStatus() == StadiumEventStatus.CANCELED);
        return result;
    }

    @PreAuthorize("hasRole('SUPERADMIN') or hasRole('EDITOR')")
    public StadiumEvent cancelEvent(Long userId, Long eventId, String reason) {
        if (reason == null || reason.isBlank()) {
            throw new IllegalArgumentException("Cancellation reason must be provided");
        }
        System.out.println("Cancellation reason: " + reason + eventId);
        StadiumEvent event = stadiumEventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));
        User currentUser = authentificationService.getUserById(userId);
        if (currentUser == null) {
            throw new IllegalArgumentException("User not found");
        }
        if (event.getStatus() == StadiumEventStatus.CANCELED) {
            throw new IllegalArgumentException("Event is already canceled");
        }
        if (currentUser.getRoles().contains(Role.EDITOR)) {
            if (currentUser.getOrganizer() != event.getOrganizer()) {
                throw new IllegalArgumentException("You are not authorized to cancel this event");
            }
        }
        event.setStatus(StadiumEventStatus.CANCELED);
        event.setDescription(reason);
        event.setTimeUpdated(LocalDateTime.now());
        actionLogService.log(
                ActionType.CANCEL_EVENT,
                currentUser
        );
        return stadiumEventRepository.save(event);
    }
}