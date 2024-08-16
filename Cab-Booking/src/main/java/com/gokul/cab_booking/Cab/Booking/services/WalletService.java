package com.gokul.cab_booking.Cab.Booking.services;

import com.gokul.cab_booking.Cab.Booking.entities.Ride;
import com.gokul.cab_booking.Cab.Booking.entities.User;
import com.gokul.cab_booking.Cab.Booking.entities.Wallet;
import com.gokul.cab_booking.Cab.Booking.entities.enums.TransactionMethod;

public interface WalletService {

    Wallet addMoneyToWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod);

    Wallet deductMoneyFromWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod);

    void withdrawAllMyMoneyFromWallet();

    Wallet findWalletById(Long walletId);

    Wallet createNewWallet(User user);

    Wallet findByUser(User user);

}
