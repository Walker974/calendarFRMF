package org.calendar.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.calendar.dto.RegisterRequestDto;
import org.calendar.entities.User;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RegisterRequestMapper {

    @Mapping(target = "id", ignore = true)
    User toEntity(RegisterRequestDto dto);
}