package com.code.booking.controller;

import com.code.booking.dto.JWTAuthRequest;
import com.code.booking.dto.JWTAuthResponse;
import com.code.booking.dto.SignInRequest;
import com.code.booking.dto.SignUpRequest;
import com.code.booking.service.AuthService;
import com.code.booking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest signUpRequest) {
        if (userService.userExists(signUpRequest.getEmail()))
            return new ResponseEntity<>("An account associated with this email already exists",
                    HttpStatus.NOT_ACCEPTABLE);
        else return new ResponseEntity<>(authService.signUp(signUpRequest), HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SignInRequest signInRequest) {
        if (!userService.userExists(signInRequest.getEmail()))
            return new ResponseEntity<>("Check your email and password", HttpStatus.NOT_ACCEPTABLE);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));
        } catch (Exception e) {
            return new ResponseEntity<>("Check your email and password", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(authService.signIn(signInRequest), HttpStatus.OK);
    }

    @PostMapping("/refresh")
    public JWTAuthResponse refresh(@RequestBody JWTAuthRequest jwtAuthRequest) {
        return authService.refreshToken(jwtAuthRequest);
    }


}
