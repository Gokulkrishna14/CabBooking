package com.gokul.cab_booking.Cab.Booking.stategies;

import com.gokul.cab_booking.Cab.Booking.dto.DriverDTO;
import com.gokul.cab_booking.Cab.Booking.dto.RideRequestDTO;

import java.util.List;

public interface DriverMatchingStrategy {

    List<DriverDTO> findMatchingDriver(RideRequestDTO rideRequestDTO);
}
