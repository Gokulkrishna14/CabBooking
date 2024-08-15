package com.gokul.cab_booking.Cab.Booking.stategies;

import com.gokul.cab_booking.Cab.Booking.stategies.impl.DriverMatchingHighestRatedDriverStrategy;
import com.gokul.cab_booking.Cab.Booking.stategies.impl.DriverMatchingNearestDriverStrategy;
import com.gokul.cab_booking.Cab.Booking.stategies.impl.RideFareDefaultFareCalculationStrategy;
import com.gokul.cab_booking.Cab.Booking.stategies.impl.RideFareSurgePricingFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class StrategyManager {

    private final DriverMatchingHighestRatedDriverStrategy driverMatchingHighestRatedDriverStrategy;

    private final DriverMatchingNearestDriverStrategy driverMatchingNearestDriverStrategy;

    private final RideFareDefaultFareCalculationStrategy rideFareDefaultFareCalculationStrategy;

    private final RideFareSurgePricingFareCalculationStrategy rideFareSurgePricingFareCalculationStrategy;

    public DriverMatchingStrategy driverMatchingStrategy(double riderRating){
        if(riderRating >= 4.8){
            return driverMatchingHighestRatedDriverStrategy;
        }else{
            return driverMatchingNearestDriverStrategy;
        }
    }

    public RideFareCalculationStrategy rideFareCalculationStrategy(){
        LocalTime surgeStartTime = LocalTime.of(18,0);

        LocalTime surgeEndTime = LocalTime.of(21,0);

        LocalTime currentTime = LocalTime.now();

        boolean isSurgeTime = currentTime.isAfter(surgeStartTime) && currentTime.isBefore(surgeEndTime);

        if(isSurgeTime){
            return rideFareSurgePricingFareCalculationStrategy;
        }else{
            return rideFareDefaultFareCalculationStrategy;
        }
    }

}
