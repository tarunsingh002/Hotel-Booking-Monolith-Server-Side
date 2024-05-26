package com.code.booking.dto;

import com.code.booking.entity.Hotel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {
    private Hotel hotel;
    private int roomsQuantity;
    private long fromDate;
    private long toDate;
    private long totalAmount;
}
