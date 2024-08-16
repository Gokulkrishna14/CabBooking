package com.gokul.cab_booking.Cab.Booking.services.impl;

import com.gokul.cab_booking.Cab.Booking.entities.WalletTransaction;
import com.gokul.cab_booking.Cab.Booking.repositories.WalletTransactionRepository;
import com.gokul.cab_booking.Cab.Booking.services.WalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletTransactionServiceImpl implements WalletTransactionService {

    private final WalletTransactionRepository walletTransactionRepository;

    private final ModelMapper modelMapper;

    @Override
    public void createNewWalletTransaction(WalletTransaction walletTransaction) {
        walletTransactionRepository.save(walletTransaction);
    }
}
