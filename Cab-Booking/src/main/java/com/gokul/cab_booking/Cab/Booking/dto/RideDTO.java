package com.gokul.cab_booking.Cab.Booking.dto;

import com.gokul.cab_booking.Cab.Booking.entities.Driver;
import com.gokul.cab_booking.Cab.Booking.entities.Rider;
import com.gokul.cab_booking.Cab.Booking.entities.enums.PaymentMethod;
import com.gokul.cab_booking.Cab.Booking.entities.enums.RideStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

public class RideDTO {

    private Long id;


    private Point pickupLocation;


    private Point dropOffLocation;


    private LocalDateTime createdTime;


    private RiderDTO rider;


    private DriverDTO driver;


    private PaymentMethod paymentMethod;


    private RideStatus rideStatus;

    private Double fare;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;
}
