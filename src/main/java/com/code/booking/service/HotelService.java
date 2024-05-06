package com.code.booking.service;

import com.code.booking.entity.Hotel;
import com.code.booking.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

    @Autowired
    private HotelRepository repository;

    public Hotel createHotel(Hotel hotel){
        return repository.save(hotel);
    }

    public List<Hotel> getHotels() {
        return repository.findAll();
    }

    public Hotel getHotelById(int id){
        return repository.findById(id).orElse(null);
    }

    public String deleteHotelById(int id){
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return "Hotel deleted";
        }
        return "Hotel requested to be deleted does not exist";
    }

    public Hotel updateHotelById(int id, Hotel hotel){
        Hotel existingHotel = repository.findById(id).orElse(null);
        if (existingHotel!=null){
            existingHotel.setAmenities(hotel.getAmenities());
            existingHotel.setName(hotel.getName());
            existingHotel.setLocation(hotel.getLocation());
            existingHotel.setDescription(hotel.getDescription());
            existingHotel.setUrl(hotel.getUrl());
            existingHotel.setPrice(hotel.getPrice());
            return repository.save(existingHotel);
        } else return null;
    }


}
