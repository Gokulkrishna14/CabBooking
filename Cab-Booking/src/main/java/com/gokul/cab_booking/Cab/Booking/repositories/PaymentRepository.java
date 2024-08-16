package com.gokul.cab_booking.Cab.Booking.repositories;

import com.gokul.cab_booking.Cab.Booking.entities.Payment;
import com.gokul.cab_booking.Cab.Booking.entities.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByRide(Ride ride);
}
