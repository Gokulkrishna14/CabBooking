package com.gokul.cab_booking.Cab.Booking.repositories;

import com.gokul.cab_booking.Cab.Booking.entities.User;
import com.gokul.cab_booking.Cab.Booking.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findByUser(User user);
}
