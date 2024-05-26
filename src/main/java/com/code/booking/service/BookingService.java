package com.code.booking.service;

import com.code.booking.dto.BookingDto;
import com.code.booking.entity.Booking;
import com.code.booking.entity.User;
import com.code.booking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository repository;

    @Autowired
    private HotelService hotelService;

    public Booking createBooking(BookingDto bookingDto, User user){

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setCreatedDate(System.currentTimeMillis());
        booking.setHotel(bookingDto.getHotel());
        booking.setFromDate(bookingDto.getFromDate());
        booking.setToDate(bookingDto.getToDate());
        booking.setRoomsQuantity(bookingDto.getRoomsQuantity());
        booking.setTotalAmount(bookingDto.getTotalAmount());

        return repository.save(booking);
    }

    public List<Booking> getUserBookings(User user){
        return repository.findAllByUser(user);
    }

    public List<Booking> getAllBookings() {
        return repository.findAll();
    }

    public Booking findBookingById(int id){
        return repository.findById(id).orElse(null);
    }
}
