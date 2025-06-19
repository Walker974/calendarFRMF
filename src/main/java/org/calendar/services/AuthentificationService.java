package org.calendar.services;

import lombok.RequiredArgsConstructor;
import org.calendar.dao.OrganizerRepository;
import org.calendar.dao.UserRepository;
import org.calendar.dto.LoginRequestDto;
import org.calendar.dto.LoginResponseDto;
import org.calendar.dto.RegisterRequestDto;
import org.calendar.entities.Organizer;
import org.calendar.entities.User;
import org.calendar.enums.Role;
import org.calendar.mappers.RegisterRequestMapper;
import org.calendar.security.JwtUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class AuthentificationService {
    private final UserRepository userRepository;
    private final OrganizerRepository organizerRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final RegisterRequestMapper registerRequestMapper;

    public LoginResponseDto login(LoginRequestDto loginRequest) {
        User user = userRepository.findByEmail(loginRequest.email())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(
                        loginRequest.email(),
                        loginRequest.password()
                );

        Authentication authentication = authenticationManager.authenticate(authToken);

        String token = jwtUtils.generateToken(authentication);

        return LoginResponseDto.builder()
                .token(token)
                .email(loginRequest.email())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRoles().stream().findFirst().orElse(Role.VIEWER))
                .organizerId(user.getOrganizer() != null ? user.getOrganizer().getId() : null)
                .build();
    }

    public void register(RegisterRequestDto dto) {
        if (userRepository.existsByEmail(dto.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already in use");
        }
        Organizer organizer = organizerRepository.findById(dto.organizer().id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Organizer not found with id: " + dto.organizer().id()));
        User user = registerRequestMapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setOrganizer(organizer);
        if (user.getRoles() == null) {
            user.setRoles(new HashSet<>());
        }
        if (dto.role() != null) {
            try {
                user.getRoles().add(Role.valueOf(dto.role()));
            } catch (IllegalArgumentException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid role: " + dto.role());
            }
        } else {
            user.getRoles().add(Role.VIEWER);
        }

        userRepository.save(user);
    }
}