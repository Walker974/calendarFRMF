package org.calendar.services;

import lombok.RequiredArgsConstructor;
import org.calendar.dao.OrganizerRepository;
import org.calendar.entities.Organizer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizerService {

    private final OrganizerRepository organizerRepository;

    public Organizer createOrganizer(Organizer organizer) {
         // TODO: add more validation as needed
        return organizerRepository.save(organizer);
    }

    public Organizer getOrganizerById(Long id) {
        return organizerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Organizer not found with id: " + id));
    }

    public List<Organizer> getAllOrganizers() {
        return organizerRepository.findAll();
    }

    public void deleteOrganizer(Long id) {
        if (!organizerRepository.existsById(id)) {
            throw new IllegalArgumentException("Organizer not found with id: " + id);
        }
        organizerRepository.deleteById(id);
    }
}
