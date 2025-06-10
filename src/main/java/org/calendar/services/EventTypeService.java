package org.calendar.services;

import lombok.RequiredArgsConstructor;
import org.calendar.dao.EventTypeRepository;
import org.springframework.stereotype.Service;
import org.calendar.entities.EventType;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventTypeService {
    private final EventTypeRepository eventTypeRepository;

    public List<EventType> getAllEventTypes() {
        return eventTypeRepository.findAll();
    }

    public EventType createEventType(EventType eventType) {
        return eventTypeRepository.save(eventType);
    }

    public EventType getDefaultEventType() {
        return eventTypeRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Default Event Type not found"));
    }

    public EventType getEventTypeById(Long id) {
        return eventTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event Type not found with id: " + id));
    }
}
