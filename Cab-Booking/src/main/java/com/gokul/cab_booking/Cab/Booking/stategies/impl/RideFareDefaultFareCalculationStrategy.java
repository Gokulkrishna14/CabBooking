package com.gokul.cab_booking.Cab.Booking.stategies.impl;

import com.gokul.cab_booking.Cab.Booking.entities.RideRequest;
import com.gokul.cab_booking.Cab.Booking.services.DistanceService;
import com.gokul.cab_booking.Cab.Booking.stategies.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideFareDefaultFareCalculationStrategy implements RideFareCalculationStrategy {
    private static final Logger log = LoggerFactory.getLogger(RideFareDefaultFareCalculationStrategy.class);
    private final DistanceService distanceService;

    @Override
    public double calculateFare(RideRequest rideRequest) {
        double distance = distanceService.calculateDistance(rideRequest.getPickupLocation(), rideRequest.getDropOffLocation());

        log.debug("distance {}", distance);
        return distance * RIDE_FARE_MULTIPLIER;
    }
}
