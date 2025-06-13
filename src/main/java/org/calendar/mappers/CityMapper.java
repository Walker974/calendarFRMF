package org.calendar.mappers;

import org.mapstruct.Mapper;
import org.calendar.dto.CityDto;
import org.calendar.entities.City;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CityMapper {
    CityDto toDto(City city);
    City toEntity(CityDto dto);
    List<CityDto> toDtoList(List<City> cities);
    List<City> toEntityList(List<CityDto> cityDtos);
}