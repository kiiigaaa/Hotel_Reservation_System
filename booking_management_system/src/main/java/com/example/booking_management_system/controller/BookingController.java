/*
 * Copyright (c) 2023. CodeGen International (Pvt) Ltd. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of CodeGen
 * International (Pvt) Ltd. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with CodeGen International.
 *
 */
package com.example.booking_management_system.controller;

import com.example.booking_management_system.dto.BookingDTO;
import com.example.booking_management_system.BookingService;
import com.example.booking_management_system.dto.ViewBookingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <b>controller class for hotel</b>
 * This controller class handles requests related to managing hotels.
 * It provides endpoints for creating hotels, updating contract details, and retrieving expired hotels.
 * Utilizes the HotelService to perform hotel-related operations.
 * @author kishen
 * @since 08 Aug 2023
 */
@RestController
@RequestMapping("/api/booking")
public class BookingController
{
    @Autowired
    private BookingService bookingService;

    /**
     * Creates a new hotel with the provided hotel details.
     *
     * @param /hotelDTO The HotelDTO containing details for creating the hotel.
     * @return Returns a ResponseEntity with the created Hotel object and HttpStatus.CREATED status.
     */
    @PostMapping("/{roomTypeId}")
    public ResponseEntity<String> createBooking(@PathVariable int roomTypeId, @RequestBody BookingDTO bookingDTO)

    {
        //I can add the createbooking to return
        bookingService.createBooking(roomTypeId, bookingDTO);
        return new ResponseEntity<>("Data inserted", HttpStatus.CREATED);
    }

    /**
     * Updates the contract details of a hotel.
     *
     * @param /hotelId         The ID of the hotel for which to update the contract.
     * @param /contractUpdateDTO The ContractDTO containing updated contract details.
     * @return Returns a ResponseEntity with a success message and HttpStatus.OK status.
     */
    @GetMapping("/view")
    public List<ViewBookingDTO> viewBookings() {
        return bookingService.getAllBookingsWithDetails();
    }

    /**
     * Retrieves a list of expired hotels.
     *
     * @return Returns a ResponseEntity with a list of Hotel objects representing expired hotels and HttpStatus.OK status.
     */
    @DeleteMapping("/{bookingId}")
    public ResponseEntity<String> deleteBooking(@PathVariable int bookingId)
    {
        bookingService.deleteBooking(bookingId);
        return new ResponseEntity<>("Booking deleted", HttpStatus.OK);
    }
}
