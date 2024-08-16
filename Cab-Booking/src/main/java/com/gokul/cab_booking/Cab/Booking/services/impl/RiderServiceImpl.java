package com.gokul.cab_booking.Cab.Booking.services.impl;

import com.gokul.cab_booking.Cab.Booking.dto.DriverDTO;
import com.gokul.cab_booking.Cab.Booking.dto.RideDTO;
import com.gokul.cab_booking.Cab.Booking.dto.RideRequestDTO;
import com.gokul.cab_booking.Cab.Booking.dto.RiderDTO;
import com.gokul.cab_booking.Cab.Booking.entities.*;
import com.gokul.cab_booking.Cab.Booking.entities.enums.RideRequestStatus;
import com.gokul.cab_booking.Cab.Booking.entities.enums.RideStatus;
import com.gokul.cab_booking.Cab.Booking.exception.ResourceNotFoundException;
import com.gokul.cab_booking.Cab.Booking.repositories.RideRequestRepository;
import com.gokul.cab_booking.Cab.Booking.repositories.RiderRepository;
import com.gokul.cab_booking.Cab.Booking.services.DriverService;
import com.gokul.cab_booking.Cab.Booking.services.RideService;
import com.gokul.cab_booking.Cab.Booking.services.RiderService;
import com.gokul.cab_booking.Cab.Booking.stategies.StrategyManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RiderServiceImpl implements RiderService {

    private final ModelMapper modelMapper;

    private final StrategyManager strategyManager;

    private final RideRequestRepository rideRequestRepository;

    private final RiderRepository riderRepository;

    private final RideService rideService;

    private final DriverService driverService;


    @Override
    @Transactional
    public RideRequestDTO requestRide(RideRequestDTO rideRequestDTO) {
        Rider rider = getCurrentRider();
        log.debug("Iniside request Ride");
        RideRequest rideRequest = modelMapper.map(rideRequestDTO, RideRequest.class);
        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);
        rideRequest.setRider(rider);
        Double fare = strategyManager.rideFareCalculationStrategy().calculateFare(rideRequest);
        rideRequest.setFare(fare);

        RideRequest savedRideRequest = rideRequestRepository.save(rideRequest);

        List<Driver> drivers= strategyManager.driverMatchingStrategy(rider.getRating()).findMatchingDriver(rideRequest);

        //TODO Send notification to all the drivers about this riderRequest

        return modelMapper.map(savedRideRequest, RideRequestDTO.class);
    }

    @Override
    public RideDTO cancelRide(Long rideId) {
        Rider rider = getCurrentRider();
        Ride ride = rideService.getRideById(rideId);

        if(!rider.equals(ride.getRider())){
            throw new RuntimeException("Rider doesn not own this ride with id "+ rideId);
        }

        if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)){
            throw new RuntimeException("Ride cannot be cancelled, invalid status "+ ride.getRideStatus());
        }

        Ride savedRide = rideService.updateRideStatus(ride, RideStatus.CANCELLED);
        driverService.updateDriverAvailability(savedRide.getDriver(), true);
        return null;
    }

    @Override
    public DriverDTO rateDriver(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public RiderDTO getMyProfile() {
        Rider currentRider = getCurrentRider();
        return modelMapper.map(currentRider, RiderDTO.class);
    }

    @Override
    public Page<RideDTO> getAllMyRides(PageRequest pageRequest) {
        Rider currentRider= getCurrentRider();
        return rideService.getAllRidesOfRider(currentRider, pageRequest).map(
                ride -> modelMapper.map(ride, RideDTO.class)
        );
    }

    @Override
    public Rider createNewRider(User user) {
        Rider rider = Rider.builder().user(user).rating(0.0).build();
        return riderRepository.save(rider);
    }

    @Override
    public Rider getCurrentRider() {
        // TODO Spring Security
        return riderRepository.findById(1L).orElseThrow(() -> new ResourceNotFoundException(
                "Rider not found with id " + 1
        ));
    }
}
