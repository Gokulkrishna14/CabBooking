package com.gokul.cab_booking.Cab.Booking.entities;

import com.gokul.cab_booking.Cab.Booking.entities.enums.PaymentMethod;
import com.gokul.cab_booking.Cab.Booking.entities.enums.PaymentStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Ride> ride;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    private Double amount;

    @CreationTimestamp
    private LocalDateTime paymentTime;

}
