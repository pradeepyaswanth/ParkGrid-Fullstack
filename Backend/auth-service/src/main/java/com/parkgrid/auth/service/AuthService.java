package com.parkgrid.auth.service;

import java.util.Random;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.parkgrid.auth.dto.AuthResponse;
import com.parkgrid.auth.dto.LoginRequest;
import com.parkgrid.auth.dto.RegisterRequest;
import com.parkgrid.auth.model.Role;
import com.parkgrid.auth.model.User;
import com.parkgrid.auth.repository.UserRepository;
import com.parkgrid.auth.security.JwtService;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public String register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            return "Email already registered";
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(
                passwordEncoder.encode(request.getPassword()));

        user.setRole(Role.USER);

        String otp = generateOtp();

        user.setOtp(otp);
        user.setOtpExpiry(
                System.currentTimeMillis() + 5 * 60 * 1000);

        user.setVerified(false);

        userRepository.save(user);

        // For development/testing
        System.out.println("OTP for " + user.getEmail() + " = " + otp);

        return "Registration successful. OTP: " + otp;
    }

    public String verifyOtp(String email, String otp) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                    new RuntimeException("User not found"));

        if (System.currentTimeMillis() > user.getOtpExpiry()) {
            return "OTP expired";
        }

        if (!user.getOtp().equals(otp)) {
            return "Invalid OTP";
        }

        user.setVerified(true);
        user.setOtp(null);

        userRepository.save(user);

        return "OTP verified successfully";
    }

    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                    new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new RuntimeException("Invalid email or password");
        }

        if (!user.isVerified()) {
            throw new RuntimeException("Please verify OTP first");
        }

        String token = jwtService.generateToken(user);

        return new AuthResponse(
                token,
                user.getRole().name(),
                "Login successful"
        );
    }

    private String generateOtp() {

        return String.format(
                "%06d",
                new Random().nextInt(1000000)
        );
    }
}