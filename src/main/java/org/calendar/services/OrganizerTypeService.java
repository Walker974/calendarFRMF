package org.calendar.services;

import lombok.RequiredArgsConstructor;
import org.calendar.dao.OrganizerTypeRepository;
import org.calendar.entities.OrganizerType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizerTypeService {

    private final OrganizerTypeRepository organizerTypeRepository;

    public OrganizerType createOrganizerType(OrganizerType organizerType) {
        if (organizerType.getTypeName() == null || organizerType.getTypeName().isEmpty()) {
            throw new IllegalArgumentException("Organizer type name cannot be null or empty");
        }
        return organizerTypeRepository.save(organizerType);
    }

    public List<OrganizerType> getAllOrganizerTypes() {
        return organizerTypeRepository.findAll();
    }

    public OrganizerType getOrganizerTypeById(Long id) {
        return organizerTypeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Organizer type not found with id: " + id));
    }
}
