package com.gokul.cab_booking.Cab.Booking.controllers;

import com.gokul.cab_booking.Cab.Booking.dto.*;
import com.gokul.cab_booking.Cab.Booking.services.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    ResponseEntity<UserDTO> signup(@RequestBody SignUpDTO signUpDTO){
        return new ResponseEntity<>(authService.signup(signUpDTO), HttpStatus.CREATED);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/onBoardNewDriver/{userId}")
    ResponseEntity<DriverDTO> onBoardNewDriver(@PathVariable Long userId,@RequestBody OnBoardDriverDTO onBoardDriverDTO){
        return new ResponseEntity<>(authService.onboardNewDriver(userId,
                onBoardDriverDTO.getVehicleId()),
                HttpStatus.CREATED);
    }

    @PostMapping("/login")
    ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginDTO,
                                           HttpServletRequest request,
                                           HttpServletResponse response){
        String[] tokens = authService.login(loginDTO.getEmail(), loginDTO.getPassword());

        Cookie cookie = new Cookie("token", tokens[1]);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        return ResponseEntity.ok(new LoginResponseDTO(tokens[0]));
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDTO> refresh(HttpServletRequest request) {
        String refreshToken = Arrays.stream(request.getCookies()).
                filter(cookie -> "refreshToken".equals(cookie.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(() -> new AuthenticationServiceException("Refresh token not found inside the Cookies"));

        String accessToken = authService.refreshToken(refreshToken);

        return ResponseEntity.ok(new LoginResponseDTO(accessToken));
    }

}
