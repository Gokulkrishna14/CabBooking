package com.gokul.cab_booking.Cab.Booking.stategies.impl;

import com.gokul.cab_booking.Cab.Booking.dto.DriverDTO;
import com.gokul.cab_booking.Cab.Booking.dto.RideRequestDTO;
import com.gokul.cab_booking.Cab.Booking.entities.RideRequest;
import com.gokul.cab_booking.Cab.Booking.repositories.DriverRepository;
import com.gokul.cab_booking.Cab.Booking.stategies.DriverMatchingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverMatchingNearestDriverStrategy implements DriverMatchingStrategy {

    private final DriverRepository driverRepository;

    @Override
    public List<DriverDTO> findMatchingDriver(RideRequest rideRequest) {
        return driverRepository.findNearestMatchingDrivers(rideRequest.getPickupLocation());

    }
}
