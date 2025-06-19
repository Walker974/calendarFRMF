package org.calendar.services;

import lombok.RequiredArgsConstructor;
import org.calendar.dao.StadiumRepository;
import org.calendar.entities.Stadium;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StadiumService {

    private final StadiumRepository stadiumRepository;

    public Stadium createStadium(Stadium stadium) {
        return stadiumRepository.save(stadium);
    }

    public List<Stadium> getAllStadiums() {
        return stadiumRepository.findAll();
    }

    public Stadium getStadiumById(Long id) {
        return stadiumRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stadium not found with id: " + id));
    }

    public Stadium updateStadium(Long stadiumId, Stadium stadium) {
        try {
            Stadium existingStadium = getStadiumById(stadiumId);
            existingStadium.setName(stadium.getName());
            existingStadium.setCity(stadium.getCity());
            return stadiumRepository.save(existingStadium);
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("Invalid stadium ID: " + stadiumId, e);
        }
    }

    public void deleteStadium(Long stadiumId) {
        try {
            Stadium stadium = getStadiumById(stadiumId);
            stadiumRepository.delete(stadium);
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("Invalid stadium ID: " + stadiumId, e);
        }
    }

    public List<Stadium> getStadiumsByCityId(Long cityId) {
        List<Stadium> stadiums = stadiumRepository.findByCityId(cityId);
        if (stadiums.isEmpty()) {
            throw new RuntimeException("No stadiums found for city with id: " + cityId);
        }
        return stadiums;
    }
}
