package com.gokul.cab_booking.Cab.Booking.services;

import com.gokul.cab_booking.Cab.Booking.dto.DriverDTO;
import com.gokul.cab_booking.Cab.Booking.dto.SignUpDTO;
import com.gokul.cab_booking.Cab.Booking.dto.UserDTO;
import com.gokul.cab_booking.Cab.Booking.entities.Driver;

public interface AuthService {

    String login(String email, String password);

    UserDTO signup(SignUpDTO signUpDTO);

    DriverDTO onboardNewDriver(Long userId);

}
