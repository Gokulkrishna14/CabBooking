package com.gokul.cab_booking.Cab.Booking.services.impl;

import com.gokul.cab_booking.Cab.Booking.dto.DriverDTO;
import com.gokul.cab_booking.Cab.Booking.dto.SignUpDTO;
import com.gokul.cab_booking.Cab.Booking.dto.UserDTO;
import com.gokul.cab_booking.Cab.Booking.entities.Driver;
import com.gokul.cab_booking.Cab.Booking.entities.User;
import com.gokul.cab_booking.Cab.Booking.entities.enums.Roles;
import com.gokul.cab_booking.Cab.Booking.exception.ResourceNotFoundException;
import com.gokul.cab_booking.Cab.Booking.exception.RuntimeConflictException;
import com.gokul.cab_booking.Cab.Booking.repositories.UserRepository;
import com.gokul.cab_booking.Cab.Booking.security.JWTService;
import com.gokul.cab_booking.Cab.Booking.services.AuthService;
import com.gokul.cab_booking.Cab.Booking.services.DriverService;
import com.gokul.cab_booking.Cab.Booking.services.RiderService;
import com.gokul.cab_booking.Cab.Booking.services.WalletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final ModelMapper modelMapper;

    private final UserRepository userRepository;

    private final RiderService riderService;

    private final WalletService walletService;

    private final DriverService driverService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;

    @Override
    public String[] login(String email, String password) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );

            User user = (User) authentication.getPrincipal();

            String accessToken = jwtService.generateAccessToken(user);
            String refreshToken = jwtService.generateRefreshToken(user);

            return new String[]{accessToken, refreshToken};
    }

    @Override
    public UserDTO signup(SignUpDTO signUpDTO) {
        Optional<User> user = userRepository.findByEmail(signUpDTO.getEmail());
        if(user.isPresent()){
            throw new RuntimeConflictException("Cannot signup, User already exists with email "+ signUpDTO.getEmail());
        }
        User mappedUser = modelMapper.map(signUpDTO, User.class);
        mappedUser.setRoles(Set.of(Roles.RIDER));
        mappedUser.setPassword(passwordEncoder.encode(mappedUser.getPassword()));
        User savedUser = userRepository.save(mappedUser);
        riderService.createNewRider(savedUser);

        walletService.createNewWallet(savedUser);

        return modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    public DriverDTO onboardNewDriver(Long userId, String vehicleId) {
        User user = userRepository.findById(userId).orElseThrow(
                ()-> new ResourceNotFoundException("User not found with id "+ userId));

        if(user.getRoles().contains(Roles.DRIVER)){
            throw new RuntimeConflictException("User with userId " + userId + " is already a driver");
        }

        Driver createNewDriver = Driver.builder()
                .user(user)
                .rating(0.0)
                .vehicleId(vehicleId)
                .available(true)
                .build();

        user.getRoles().add(Roles.DRIVER);
        userRepository.save(user);

        Driver savedDriver = driverService.createNewDriver(createNewDriver);

        return  modelMapper.map(savedDriver, DriverDTO.class);

    }

    @Override
    public String refreshToken(String refreshToken) {
        Long userId = jwtService.getUserIdFromToken(refreshToken);
        User user = userRepository.findById(userId).orElseThrow(
                ()-> new ResourceNotFoundException("User not found with id "+ userId));
        return jwtService.generateRefreshToken(user);
    }

}
