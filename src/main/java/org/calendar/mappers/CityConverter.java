package org.calendar.mappers;

import org.calendar.entities.City;
import org.calendar.dto.CityDto;
import java.util.List;
import java.util.stream.Collectors;

public class CityConverter {
    public static CityDto toDto(City city) {
        if(city == null) {
            return null;
        }
        CityDto cityDto = new CityDto(
                city.getId(),
                city.getName()
        );
        return cityDto;
    }

    public static City toEntity(CityDto dto) {
        if(dto == null) {
            return null;
        }
        City city = new City();
        city.setId(dto.id());
        city.setName(dto.name());
        return city;
    }

    public static List<CityDto> toDtoList(List<City> cities) {
        return cities.stream()
                .map(CityConverter::toDto)
                .collect(Collectors.toList());
    }
}
