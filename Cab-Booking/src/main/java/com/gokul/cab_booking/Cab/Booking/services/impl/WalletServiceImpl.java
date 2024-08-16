package com.gokul.cab_booking.Cab.Booking.services.impl;

import com.gokul.cab_booking.Cab.Booking.entities.Ride;
import com.gokul.cab_booking.Cab.Booking.entities.User;
import com.gokul.cab_booking.Cab.Booking.entities.Wallet;
import com.gokul.cab_booking.Cab.Booking.entities.WalletTransaction;
import com.gokul.cab_booking.Cab.Booking.entities.enums.TransactionMethod;
import com.gokul.cab_booking.Cab.Booking.entities.enums.TransactionType;
import com.gokul.cab_booking.Cab.Booking.exception.ResourceNotFoundException;
import com.gokul.cab_booking.Cab.Booking.repositories.WalletRepository;
import com.gokul.cab_booking.Cab.Booking.services.WalletService;
import com.gokul.cab_booking.Cab.Booking.services.WalletTransactionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    private final WalletTransactionService walletTransactionService;


    @Override
    @Transactional
    public Wallet addMoneyToWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod) {
        Wallet wallet = findByUser(user);
        wallet.setBalance(wallet.getBalance() + amount);

        WalletTransaction walletTransaction = WalletTransaction.builder()
                .transactionId(transactionId)
                .ride(ride)
                .wallet(wallet)
                .amount(amount)
                .transactionType(TransactionType.CREDIT)
                .transactionMethod(transactionMethod)
                .build();

        walletTransactionService.createNewWalletTransaction(walletTransaction);

        return walletRepository.save(wallet);
    }

    @Override
    @Transactional
    public Wallet deductMoneyFromWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod) {
        Wallet wallet = findByUser(user);
        wallet.setBalance(wallet.getBalance() - amount);

        WalletTransaction walletTransaction = WalletTransaction.builder()
                .transactionId(transactionId)
                .ride(ride)
                .wallet(wallet)
                .amount(amount)
                .transactionType(TransactionType.DEBIT)
                .transactionMethod(transactionMethod)
                .build();

        walletTransactionService.createNewWalletTransaction(walletTransaction);

        return walletRepository.save(wallet);
    }

    @Override
    public void withdrawAllMyMoneyFromWallet() {

    }

    @Override
    public Wallet findWalletById(Long walletId) {
        return walletRepository.findById(walletId).orElseThrow(() ->
                new ResourceNotFoundException("Wallet not found with id "+ walletId));
    }

    @Override
    public Wallet createNewWallet(User user) {
        Wallet wallet= new Wallet();
        wallet.setUser(user);
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet findByUser(User user) {
        return walletRepository.findByUser(user).orElseThrow(() ->
                new ResourceNotFoundException("Wallet not found for user with UserId "+ user.getId()));
    }
}
