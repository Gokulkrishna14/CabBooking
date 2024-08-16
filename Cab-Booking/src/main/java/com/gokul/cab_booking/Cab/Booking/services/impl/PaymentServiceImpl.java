package com.gokul.cab_booking.Cab.Booking.services.impl;

import com.gokul.cab_booking.Cab.Booking.entities.Payment;
import com.gokul.cab_booking.Cab.Booking.entities.Ride;
import com.gokul.cab_booking.Cab.Booking.entities.enums.PaymentStatus;
import com.gokul.cab_booking.Cab.Booking.exception.ResourceNotFoundException;
import com.gokul.cab_booking.Cab.Booking.repositories.PaymentRepository;
import com.gokul.cab_booking.Cab.Booking.services.PaymentService;
import com.gokul.cab_booking.Cab.Booking.stategies.PaymentStrategyManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    private final PaymentStrategyManager paymentStrategyManager;


    @Override
    public void processPayment(Ride ride) {
        Payment payment = paymentRepository.findByRide(ride).orElseThrow(() ->
                new ResourceNotFoundException("Payment not found for ride with id "+ ride.getId()));
        paymentStrategyManager.paymentStrategy(payment.getPaymentMethod()).processPayment(payment);
    }

    @Override
    public Payment createNewPayment(Ride ride) {
        Payment payment = Payment.builder()
                .ride(ride)
                .paymentMethod(ride.getPaymentMethod())
                .amount(ride.getFare())
                .paymentStatus(PaymentStatus.PENDING)
                .build();

        return paymentRepository.save(payment);
    }

    @Override
    public void updatePaymentStatus(Payment payment, PaymentStatus paymentStatus) {
        payment.setPaymentStatus(paymentStatus);
        paymentRepository.save(payment);
    }
}
