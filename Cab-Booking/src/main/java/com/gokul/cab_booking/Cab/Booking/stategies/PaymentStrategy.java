package com.gokul.cab_booking.Cab.Booking.stategies;


import com.gokul.cab_booking.Cab.Booking.entities.Payment;

public interface PaymentStrategy{

    Double PLATFORM_COMMISSION= 0.3;

    void processPayment(Payment payment);
}
