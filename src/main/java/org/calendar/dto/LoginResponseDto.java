package org.calendar.dto;

import lombok.Builder;
import org.calendar.enums.Role;

@Builder
public record LoginResponseDto(String token, String firstName, String lastName, String email, Role role, Long organizerId, Long userId) {}
