package com.gokul.cab_booking.Cab.Booking.stategies.impl;

import com.gokul.cab_booking.Cab.Booking.dto.DriverDTO;
import com.gokul.cab_booking.Cab.Booking.dto.RideRequestDTO;
import com.gokul.cab_booking.Cab.Booking.stategies.DriverMatchingStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverMatchingNearestDriverStrategy implements DriverMatchingStrategy {

    @Override
    public List<DriverDTO> findMatchingDriver(RideRequestDTO rideRequestDTO) {
        return List.of();
    }
}
