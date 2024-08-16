package com.gokul.cab_booking.Cab.Booking.stategies;

import com.gokul.cab_booking.Cab.Booking.entities.enums.PaymentMethod;
import com.gokul.cab_booking.Cab.Booking.stategies.impl.CashPaymentStrategy;
import com.gokul.cab_booking.Cab.Booking.stategies.impl.WalletPaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentStrategyManager {

    private final CashPaymentStrategy cashPaymentStrategy;

    private final WalletPaymentStrategy walletPaymentStrategy;


    public PaymentStrategy paymentStrategy(PaymentMethod paymentMethod){
        return switch (paymentMethod){
            case PaymentMethod.CASH -> cashPaymentStrategy;
            case PaymentMethod.WALLET -> walletPaymentStrategy;
        };
    }
}
