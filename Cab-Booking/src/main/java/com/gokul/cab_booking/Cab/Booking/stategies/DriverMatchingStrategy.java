package com.gokul.cab_booking.Cab.Booking.stategies;

import com.gokul.cab_booking.Cab.Booking.dto.DriverDTO;
import com.gokul.cab_booking.Cab.Booking.dto.RideRequestDTO;
import com.gokul.cab_booking.Cab.Booking.entities.RideRequest;
import com.gokul.cab_booking.Cab.Booking.repositories.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DriverMatchingStrategy {


    List<DriverDTO> findMatchingDriver(RideRequest rideRequest);
}
