package org.calendar.services;

import lombok.RequiredArgsConstructor;
import org.calendar.dao.StadiumEventRepository;
import org.calendar.entities.StadiumEvent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StadiumEventService {
    private final StadiumEventRepository stadiumEventRepository;

    public StadiumEvent createEvent(StadiumEvent event) {
        List<StadiumEvent> events = stadiumEventRepository.findByStadium(event.getStadium());
        for (StadiumEvent existing : events) {
            if (event.getStartTime().isBefore(existing.getEndTime())
                    && event.getEndTime().isAfter(existing.getStartTime())) {
                throw new IllegalArgumentException("Stadium is already booked for the selected times");
            }
        }
        return stadiumEventRepository.save(event);
    }

    public List<StadiumEvent> getAllEvents() {
        return stadiumEventRepository.findAll();
    }
}
