package com.code.booking.service;

import com.code.booking.dto.HotelResponse;
import com.code.booking.entity.Hotel;
import com.code.booking.repository.HotelRepository;
import com.code.booking.specification.HotelSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

    @Autowired
    private HotelRepository repository;

    public Hotel createHotel(Hotel hotel){
        return repository.save(hotel);
    }

    public HotelResponse getHotels(int pageSize, int pageNumber, String sortBy, String direction) {

        if (pageNumber != -1) {
            Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
            Pageable p = PageRequest.of(pageNumber, pageSize, sort);

            Page<Hotel> pageHotel = repository.findAll(p);

            return HotelResponse.builder().hotels(pageHotel.getContent())
                    .lastPage(pageHotel.isLast()).pageNumber(pageHotel.getNumber())
                    .totalElements(pageHotel.getTotalElements()).pageSize(pageHotel.getSize())
                    .totalPages(pageHotel.getTotalPages()).build();
        } else {
            List<Hotel> hotels = repository.findAll();
            return HotelResponse.builder().hotels(hotels)
                    .lastPage(true).pageNumber(-1)
                    .totalElements(hotels.size()).pageSize(hotels.size())
                    .totalPages(1).build();

        }
    }

    public HotelResponse filterHotels(int pageSize, int pageNumber, String searchTerm, List<String> locations, List<String> amenities,float minPrice, float maxPrice, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable p = PageRequest.of(pageNumber, pageSize, sort);

        Specification<Hotel> locationsSpecification = null;
        for (String location : locations) {
            Specification<Hotel> locationSpecification = HotelSpecification.locationContains(location);
            if (locationsSpecification == null) {
                locationsSpecification = locationSpecification;
            } else {
                locationsSpecification = locationsSpecification.or(locationSpecification);
            }
        }

        Specification<Hotel> amenitiesSpecification = null;

        for (String amenity : amenities) {
            Specification<Hotel> amenitySpecification = HotelSpecification.amenitiesContains(amenity);
            if (amenitiesSpecification == null) {
                amenitiesSpecification = amenitySpecification;
            } else {
                amenitiesSpecification = amenitiesSpecification.or(amenitySpecification);
            }
        }

        Specification<Hotel> searchSpecification = null;

        if (searchTerm.trim().length() != 0) {
            Specification<Hotel> searchLocationSpecification = HotelSpecification.locationContains(searchTerm);
            Specification<Hotel> searchNameSpecification = HotelSpecification.nameContains(searchTerm);
            searchSpecification = searchLocationSpecification.or(searchNameSpecification);
        }

        Specification<Hotel> combinedSpecification1 = null;

        if (locationsSpecification != null && amenitiesSpecification != null && searchSpecification != null)
            combinedSpecification1 = locationsSpecification.and(amenitiesSpecification).and(searchSpecification);
        else if (locationsSpecification == null && amenitiesSpecification != null && searchSpecification != null)
            combinedSpecification1 = amenitiesSpecification.and(searchSpecification);
        else if (locationsSpecification == null && amenitiesSpecification == null && searchSpecification != null)
            combinedSpecification1 = searchSpecification;
        else if (locationsSpecification == null && amenitiesSpecification != null && searchSpecification == null)
            combinedSpecification1 = amenitiesSpecification;
        else if (locationsSpecification != null && amenitiesSpecification == null && searchSpecification == null)
            combinedSpecification1 = locationsSpecification;
        else if (locationsSpecification != null && amenitiesSpecification == null && searchSpecification != null)
            combinedSpecification1 = locationsSpecification.and(searchSpecification);
        else if (locationsSpecification != null && amenitiesSpecification != null && searchSpecification == null)
            combinedSpecification1 = locationsSpecification.and(amenitiesSpecification);

        Specification<Hotel> minPriceSpecification = null;
        if (minPrice!=-1) minPriceSpecification = HotelSpecification.priceMoreThanOrEqualTo(minPrice);

        Specification<Hotel> maxPriceSpecification = null;
        if (maxPrice!=-1) maxPriceSpecification = HotelSpecification.priceLessThanOrEqualTo(maxPrice);

        Specification<Hotel> combinedSpecification2 = null;
        if (minPriceSpecification!=null && maxPriceSpecification!=null)
            combinedSpecification2 = minPriceSpecification.and(maxPriceSpecification);
        if (minPriceSpecification!=null && maxPriceSpecification==null)
            combinedSpecification2 = minPriceSpecification;
        if (minPriceSpecification==null && maxPriceSpecification!=null)
            combinedSpecification2 = maxPriceSpecification;

        Specification<Hotel> combinedSpecification = null;
        if (combinedSpecification1!=null && combinedSpecification2!=null)
            combinedSpecification = combinedSpecification1.and(combinedSpecification2);
        if (combinedSpecification1!=null && combinedSpecification2==null)
            combinedSpecification = combinedSpecification1;
        if (combinedSpecification1==null && combinedSpecification2!=null)
            combinedSpecification = combinedSpecification2;

        Page<Hotel> pageHotel = combinedSpecification != null ? repository.findAll(combinedSpecification, p) : repository.findAll(p);

        return HotelResponse.builder().hotels(pageHotel.getContent()).totalPages(pageHotel.getTotalPages())
                .totalElements(pageHotel.getTotalElements()).lastPage(pageHotel.isLast())
                .pageSize(pageHotel.getSize()).pageNumber(pageHotel.getNumber()).build();
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
