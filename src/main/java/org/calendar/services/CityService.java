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

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public City createCity(City city) {
        return cityRepository.save(city);
    }
}

