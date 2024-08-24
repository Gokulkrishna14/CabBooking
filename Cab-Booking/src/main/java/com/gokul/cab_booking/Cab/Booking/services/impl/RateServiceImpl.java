package com.gokul.cab_booking.Cab.Booking.services.impl;

import com.gokul.cab_booking.Cab.Booking.dto.DriverDTO;
import com.gokul.cab_booking.Cab.Booking.dto.RiderDTO;
import com.gokul.cab_booking.Cab.Booking.entities.Driver;
import com.gokul.cab_booking.Cab.Booking.entities.Rating;
import com.gokul.cab_booking.Cab.Booking.entities.Ride;
import com.gokul.cab_booking.Cab.Booking.entities.Rider;
import com.gokul.cab_booking.Cab.Booking.exception.ResourceNotFoundException;
import com.gokul.cab_booking.Cab.Booking.exception.RuntimeConflictException;
import com.gokul.cab_booking.Cab.Booking.repositories.DriverRepository;
import com.gokul.cab_booking.Cab.Booking.repositories.RatingRepository;
import com.gokul.cab_booking.Cab.Booking.repositories.RiderRepository;
import com.gokul.cab_booking.Cab.Booking.services.RateService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RateServiceImpl implements RateService {

    private final RatingRepository ratingRepository;

    private final DriverRepository driverRepository;

    private final RiderRepository riderRepository;

    private final ModelMapper modelMapper;


    @Override
    public DriverDTO rateDriver(Ride ride, Integer rating) {
        Driver driver = ride.getDriver();
        Rating ratingObj = ratingRepository.findByRide(ride).orElseThrow(() ->
                new ResourceNotFoundException("Rating not found for ride with id "+ ride.getId()));
        if(ratingObj.getDriverRating() != null){
            throw new RuntimeConflictException("Driver is been already rated");
        }

        ratingObj.setDriverRating(rating);
        ratingRepository.save(ratingObj);

        Double newDriverRating = ratingRepository.findByDriver(driver)
                .stream()
                .mapToDouble(Rating::getDriverRating)
                .average().orElse(0.0);

        driver.setRating(newDriverRating);

        Driver savedDriver = driverRepository.save(driver);

        return modelMapper.map(savedDriver, DriverDTO.class);
    }

    @Override
    public RiderDTO rateRider(Ride ride, Integer rating) {
        Rider rider = ride.getRider();
        Rating ratingObj = ratingRepository.findByRide(ride).orElseThrow(() ->
                new ResourceNotFoundException("Rating not found for ride with id "+ ride.getId()));

        if(ratingObj.getRiderRating() != null){
            throw new RuntimeConflictException("Rider is been already rated");
        }

        ratingObj.setRiderRating(rating);
        ratingRepository.save(ratingObj);

        Double newRiderRating = ratingRepository.findByRider(rider)
                .stream()
                .mapToDouble(rating1 -> rating1.getRiderRating())
                .average().orElse(0.0);

        rider.setRating(newRiderRating);

        Rider savedRider = riderRepository.save(rider);

        return modelMapper.map(savedRider, RiderDTO.class);
    }

    @Override
    public void createNewRating(Ride ride) {
        Rating rating = Rating.builder()
                .rider(ride.getRider())
                .driver(ride.getDriver())
                .ride(ride)
                .build();

        ratingRepository.save(rating);
    }
}
