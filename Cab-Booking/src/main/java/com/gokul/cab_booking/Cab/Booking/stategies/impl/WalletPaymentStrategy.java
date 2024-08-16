package com.gokul.cab_booking.Cab.Booking.stategies.impl;

import com.gokul.cab_booking.Cab.Booking.entities.Driver;
import com.gokul.cab_booking.Cab.Booking.entities.Payment;
import com.gokul.cab_booking.Cab.Booking.entities.Rider;
import com.gokul.cab_booking.Cab.Booking.entities.enums.PaymentStatus;
import com.gokul.cab_booking.Cab.Booking.entities.enums.TransactionMethod;
import com.gokul.cab_booking.Cab.Booking.repositories.PaymentRepository;
import com.gokul.cab_booking.Cab.Booking.services.PaymentService;
import com.gokul.cab_booking.Cab.Booking.services.WalletService;
import com.gokul.cab_booking.Cab.Booking.stategies.PaymentStrategy;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;

    private final PaymentRepository paymentRepository;


    @Override
    @Transactional
    public void processPayment(Payment payment) {
        Driver driver = payment.getRide().getDriver();
        Rider rider = payment.getRide().getRider();

        walletService.deductMoneyFromWallet(rider.getUser(), payment.getAmount(),
                null, payment.getRide(), TransactionMethod.RIDE);

        double driversCut = payment.getAmount() * (1- PLATFORM_COMMISSION);

        walletService.addMoneyToWallet(driver.getUser(), driversCut,
                null, payment.getRide(), TransactionMethod.RIDE);

        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);

    }
}
