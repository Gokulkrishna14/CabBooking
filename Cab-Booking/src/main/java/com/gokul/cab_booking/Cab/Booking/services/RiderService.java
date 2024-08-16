package com.gokul.cab_booking.Cab.Booking.services;

import com.gokul.cab_booking.Cab.Booking.dto.DriverDTO;
import com.gokul.cab_booking.Cab.Booking.dto.RideDTO;
import com.gokul.cab_booking.Cab.Booking.dto.RideRequestDTO;
import com.gokul.cab_booking.Cab.Booking.dto.RiderDTO;
import com.gokul.cab_booking.Cab.Booking.entities.Rider;
import com.gokul.cab_booking.Cab.Booking.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RiderService {

    RideRequestDTO requestRide(RideRequestDTO rideRequestDTO);

    RideDTO cancelRide(Long rideId);


    DriverDTO rateDriver(Long rideId, Integer rating);

    RiderDTO getMyProfile();

    Page<RideDTO> getAllMyRides(PageRequest pageRequest);

    Rider createNewRider(User user);

    Rider getCurrentRider();
}
