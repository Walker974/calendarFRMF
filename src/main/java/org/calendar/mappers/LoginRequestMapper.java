package org.calendar.mappers;

import org.mapstruct.Mapper;
import org.calendar.dto.LoginRequestDto;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Mapper(componentModel = "spring")
public interface LoginRequestMapper {
    default UsernamePasswordAuthenticationToken toAuthenticationToken(LoginRequestDto dto) {
        return new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
    }
}