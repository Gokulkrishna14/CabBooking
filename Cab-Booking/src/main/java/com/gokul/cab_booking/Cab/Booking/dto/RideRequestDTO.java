package com.gokul.cab_booking.Cab.Booking.dto;

import com.gokul.cab_booking.Cab.Booking.entities.Rider;
import com.gokul.cab_booking.Cab.Booking.entities.enums.PaymentMethod;
import com.gokul.cab_booking.Cab.Booking.entities.enums.RideRequestStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideRequestDTO {

    private Long id;


    private PointDTO pickupLocation;


    private PointDTO dropOffLocation;


    private LocalDateTime requestedTime;


    private RiderDTO rider;


    private PaymentMethod paymentMethod;


    private RideRequestStatus rideRequestStatus;
}
