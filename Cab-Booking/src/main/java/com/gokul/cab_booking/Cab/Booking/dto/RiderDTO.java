package com.gokul.cab_booking.Cab.Booking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RiderDTO {

    private UserDTO user;

    private Double rating;
}