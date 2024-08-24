package com.gokul.cab_booking.Cab.Booking.repositories;

import com.gokul.cab_booking.Cab.Booking.entities.Rider;
import com.gokul.cab_booking.Cab.Booking.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RiderRepository extends JpaRepository<Rider, Long> {
    Optional<Rider> findByUser(User user);
}
