package com.code.booking.repository;

import com.code.booking.entity.Booking;
import com.code.booking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Integer> {
    List<Booking> findAllByUser(User user);
}
