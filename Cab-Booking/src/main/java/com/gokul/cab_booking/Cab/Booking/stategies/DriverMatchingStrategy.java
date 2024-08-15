package com.gokul.cab_booking.Cab.Booking.stategies;

import com.gokul.cab_booking.Cab.Booking.entities.Driver;
import com.gokul.cab_booking.Cab.Booking.entities.RideRequest;

import java.util.List;

public interface DriverMatchingStrategy {


    List<Driver> findMatchingDriver(RideRequest rideRequest);
}
