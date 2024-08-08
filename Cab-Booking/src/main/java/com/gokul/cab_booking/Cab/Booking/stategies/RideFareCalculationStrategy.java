package com.gokul.cab_booking.Cab.Booking.stategies;

import com.gokul.cab_booking.Cab.Booking.dto.RideRequestDTO;
import com.gokul.cab_booking.Cab.Booking.entities.RideRequest;

public interface RideFareCalculationStrategy {

    double RIDE_FARE_MULTIPLIER= 10;

    double calculateFare(RideRequest rideRequest);
}
