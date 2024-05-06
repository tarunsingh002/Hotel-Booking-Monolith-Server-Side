package com.code.booking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "booking2")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private long createdDate;

    private long fromDate;

    private long toDate;

    private long totalAmount;

    private int roomsQuantity;

    @OneToOne
    @JoinColumn(name = "hotelId",referencedColumnName = "hotelId")
    private Hotel hotel;

    @OneToOne
    @JoinColumn(name = "userId",referencedColumnName = "userId")
    private User user;
}
