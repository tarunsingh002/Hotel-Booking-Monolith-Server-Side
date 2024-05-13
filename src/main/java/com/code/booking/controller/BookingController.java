package com.code.booking.controller;

import com.code.booking.dto.BookingDto;
import com.code.booking.entity.Booking;
import com.code.booking.entity.User;
import com.code.booking.service.BookingService;
import com.code.booking.service.JWTService;
import com.code.booking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookingController {

    @Autowired
    private BookingService service;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/v1/user/createbooking")
    public Booking createBooking(@RequestHeader("Authorization") String token, @RequestBody BookingDto bookingDto){
        String userEmail = jwtService.extractUsername(token.substring(7));
        if (userService.userExists(userEmail))
            return service.createBooking(bookingDto, (User) userService.loadUserByUsername(userEmail));

        return null;
    }

    @GetMapping("/api/v1/user/getuserbookings")
    public List<Booking> getUserBookings(@RequestHeader("Authorization") String token){
        String userEmail = jwtService.extractUsername(token.substring(7));
        if (userService.userExists(userEmail))
            return service.getUserBookings( (User) userService.loadUserByUsername(userEmail));

        return null;

    }

    @GetMapping("/api/v1/admin/getallbookings")
    public List<Booking> getAllBookings(){
        return service.getAllBookings();
    }

    @GetMapping("/api/v1/admin/getbooking/{id}")
    public Booking getBooking(@PathVariable int id){
        return service.findBookingById(id);
    }
}
