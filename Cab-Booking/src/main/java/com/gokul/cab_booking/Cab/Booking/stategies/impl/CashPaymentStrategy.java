package com.gokul.cab_booking.Cab.Booking.stategies.impl;

import com.gokul.cab_booking.Cab.Booking.entities.Driver;
import com.gokul.cab_booking.Cab.Booking.entities.Payment;
import com.gokul.cab_booking.Cab.Booking.entities.enums.PaymentStatus;
import com.gokul.cab_booking.Cab.Booking.entities.enums.TransactionMethod;
import com.gokul.cab_booking.Cab.Booking.repositories.PaymentRepository;
import com.gokul.cab_booking.Cab.Booking.services.PaymentService;
import com.gokul.cab_booking.Cab.Booking.services.WalletService;
import com.gokul.cab_booking.Cab.Booking.stategies.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;

    private final PaymentRepository paymentRepository;

    @Override
    public void processPayment(Payment payment) {
        Driver driver = payment.getRide().getDriver();

        double platformCommission = payment.getAmount() * PLATFORM_COMMISSION;

        walletService.deductMoneyFromWallet(driver.getUser(), platformCommission,
                null, payment.getRide(), TransactionMethod.RIDE);

        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);


    }
}
