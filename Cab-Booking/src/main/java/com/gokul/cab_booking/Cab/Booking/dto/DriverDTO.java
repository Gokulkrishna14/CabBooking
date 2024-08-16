package com.gokul.cab_booking.Cab.Booking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverDTO {

    private Long id;

    private UserDTO user;

    private Double rating;

    private String vehicleId;

    private Boolean available;
}
