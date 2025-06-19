package org.calendar.services;

import lombok.RequiredArgsConstructor;
import org.calendar.dao.CityRepository;
import org.calendar.entities.City;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;
    private final StadiumService stadiumService;

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public City createCity(City city) {
        return cityRepository.save(city);
    }

    public City getCityById(Long id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("City not found with id: " + id));
    }

    public City updateCity(Long cityId, City city) {
        try {
            City existingCity = getCityById(cityId);
            existingCity.setName(city.getName());
            return cityRepository.save(existingCity);
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("Invalid city ID: " + cityId, e);
        }
    }

    public void deleteCity(Long cityId) {
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid city ID: " + cityId));

        if (!stadiumService.getStadiumsByCityId(cityId).isEmpty()) {
            throw new IllegalArgumentException("Cannot delete city with associated stadiums");
        }
        cityRepository.delete(city);
    }
}

