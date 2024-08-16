package com.gokul.cab_booking.Cab.Booking.services;

import com.gokul.cab_booking.Cab.Booking.entities.Payment;
import com.gokul.cab_booking.Cab.Booking.entities.Ride;
import com.gokul.cab_booking.Cab.Booking.entities.enums.PaymentStatus;

public interface PaymentService {

    void processPayment(Ride ride);

    Payment createNewPayment(Ride ride);

    void updatePaymentStatus(Payment payment, PaymentStatus paymentStatus);
}
