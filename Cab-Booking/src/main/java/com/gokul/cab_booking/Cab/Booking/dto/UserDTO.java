package com.gokul.cab_booking.Cab.Booking.dto;

import com.gokul.cab_booking.Cab.Booking.entities.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String name;

    private String email;

    private Set<Roles> roles;
}
