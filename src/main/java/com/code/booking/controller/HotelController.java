package com.code.booking.controller;

import com.code.booking.entity.Hotel;
import com.code.booking.repository.HotelRepository;
import com.code.booking.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
public class HotelController {

    @Autowired
    private HotelService service;

    @PostMapping("/api/v1/admin/createhotel")
    public Hotel createHotel(@RequestBody Hotel hotel){
        return service.createHotel(hotel);
    }

    @GetMapping("/api/v1/all/getallhotels")
    public List<Hotel> getAllHotels(){
        return service.getHotels();
    }

    @GetMapping("/api/v1/all/gethotel")
    public Hotel getHotelById(@PathVariable int id){
        return service.getHotelById(id);
    }

    @PutMapping("/api/v1/admin/upadatehotel/{id}")
    public Hotel updateHotel(@PathVariable int id, @RequestBody Hotel hotel){
        return service.updateHotelById(id,hotel);
    }

    @DeleteMapping("/api/v1/admin/deletehotel/{id}")
    public String deleteHotel(@PathVariable int id){
        return service.deleteHotelById(id);
    }



}
