package com.code.booking.controller;

import com.code.booking.dto.FilterDto;
import com.code.booking.dto.HotelResponse;
import com.code.booking.entity.Hotel;
import com.code.booking.repository.HotelRepository;
import com.code.booking.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HotelController {

    @Autowired
    private HotelService service;

    @PostMapping("/api/v1/admin/createhotel")
    public Hotel createHotel(@RequestBody Hotel hotel){
        return service.createHotel(hotel);
    }

    @GetMapping("/api/v1/all/getallhotels")
    public HotelResponse getAllHotels(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
                                      @RequestParam(value = "pageSize", defaultValue = "9", required = false) int pageSize,
                                      @RequestParam(value = "sortBy", defaultValue = "hotelId", required = false) String sortBy,
                                      @RequestParam(value = "direction", defaultValue = "asc", required = false) String direction){
        return service.getHotels(pageSize, pageNumber, sortBy, direction);
    }

    @PostMapping("/api/v1/all/filterhotels")
    public HotelResponse filterHotels(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
                                          @RequestParam(value = "pageSize", defaultValue = "9", required = false) int pageSize,
                                          @RequestParam(value = "sortBy", defaultValue = "hotelId", required = false) String sortBy,
                                          @RequestParam(value = "direction", defaultValue = "asc", required = false) String direction,
                                          @RequestParam(value = "searchTerm", defaultValue = "", required = false) String searchTerm,
                                          @RequestParam(value = "minPrice", defaultValue = "-1", required = false) float minPrice,
                                          @RequestParam(value = "maxPrice", defaultValue = "-1", required = false) float maxPrice,
                                          @RequestBody FilterDto filterDto
    ) {
        return service.filterHotels(pageSize, pageNumber, searchTerm, filterDto.getLocations(), filterDto.getAmenities(),minPrice,maxPrice, sortBy, direction);
    }

    @GetMapping("/api/v1/all/gethotel/{id}")
    public Hotel getHotelById(@PathVariable int id){
        return service.getHotelById(id);
    }

    @PutMapping("/api/v1/admin/updatehotel/{id}")
    public Hotel updateHotel(@PathVariable int id, @RequestBody Hotel hotel){
        return service.updateHotelById(id,hotel);
    }

    @DeleteMapping("/api/v1/admin/deletehotel/{id}")
    public String deleteHotel(@PathVariable int id){
        return service.deleteHotelById(id);
    }



}
