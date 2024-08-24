package com.gokul.cab_booking.Cab.Booking.controllers;

import com.gokul.cab_booking.Cab.Booking.dto.DriverDTO;
import com.gokul.cab_booking.Cab.Booking.dto.OnBoardDriverDTO;
import com.gokul.cab_booking.Cab.Booking.dto.SignUpDTO;
import com.gokul.cab_booking.Cab.Booking.dto.UserDTO;
import com.gokul.cab_booking.Cab.Booking.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    ResponseEntity<UserDTO> signup(@RequestBody SignUpDTO signUpDTO){
        return new ResponseEntity<>(authService.signup(signUpDTO), HttpStatus.CREATED);
    }

    @PostMapping("/onBoardNewDriver/{userId}")
    ResponseEntity<DriverDTO> onBoardNewDriver(@PathVariable Long userId,@RequestBody OnBoardDriverDTO onBoardDriverDTO){
        return new ResponseEntity<>(authService.onboardNewDriver(userId,
                onBoardDriverDTO.getVehicleId()),
                HttpStatus.CREATED);
    }

//    @PostMapping("/login")
//    ResponseEntity<UserDTO> login(@RequestBody Lo)
}
