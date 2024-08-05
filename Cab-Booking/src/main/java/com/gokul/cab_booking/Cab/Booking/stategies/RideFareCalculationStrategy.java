package com.gokul.cab_booking.Cab.Booking.stategies;

import com.gokul.cab_booking.Cab.Booking.dto.RideRequestDTO;

public interface RideFareCalculationStrategy {

    double calculateFare(RideRequestDTO rideRequestDTO);
}
