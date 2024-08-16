package com.gokul.cab_booking.Cab.Booking.repositories;

import com.gokul.cab_booking.Cab.Booking.entities.WalletTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletTransactionRepository extends JpaRepository<WalletTransaction, Long> {
}
