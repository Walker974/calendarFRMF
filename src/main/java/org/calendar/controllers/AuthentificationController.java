package org.calendar.controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.calendar.dto.LoginRequestDto;
import org.calendar.dto.LoginResponseDto;
import org.calendar.dto.RegisterRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.calendar.services.AuthentificationService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AuthentificationController {

    private final AuthentificationService authentificationService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @Operation(summary = "User login", description = "Authenticate user and return JWT token")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequest) {
        return ResponseEntity.ok(authentificationService.login(loginRequest));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @Operation(summary = "User registration", description = "Register a new user and return JWT token")
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterRequestDto registerRequest) {
        authentificationService.register(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
