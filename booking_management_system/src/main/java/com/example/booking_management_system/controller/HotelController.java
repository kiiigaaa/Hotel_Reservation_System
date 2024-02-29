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

import com.example.booking_management_system.dto.ContractDTO;
import com.example.booking_management_system.dto.HotelDTO;
import com.example.booking_management_system.HotelService;
import com.example.booking_management_system.entity.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <b>controller class for hotel</b>
 * This controller class handles requests related to managing rooms.
 * It provides endpoints for creating rooms and retrieving rooms by hotel ID.
 * Utilizes the RoomService to perform room-related operations.
 * @author kishen
 * @since 07 Aug 2023
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/hotels")

public class HotelController
{
    @Autowired
    private HotelService hotelService;

    /**
     * Creates a new hotel with the provided hotel details.
     *
     * @param /hotelDTO The HotelDTO containing details for creating the hotel.
     * @return Returns a ResponseEntity with the created Hotel object and HttpStatus.CREATED status.
     */
    @PostMapping("/saveHotel")
    public ResponseEntity<Hotel> createHotel( @RequestBody HotelDTO hotelDTO)
    {
        Hotel createdHotel = hotelService.createHotel(hotelDTO);
        return new ResponseEntity<>(createdHotel, HttpStatus.CREATED);
    }

    /**
     * Updates the contract details of a hotel.
     *
     * @param /hotelId         The ID of the hotel for which to update the contract.
     * @param /contractUpdateDTO The ContractDTO containing updated contract details.
     * @return Returns a ResponseEntity with a success message and HttpStatus.OK status.
     */
    @PatchMapping("/update-contract/{hotelId}")
    public ResponseEntity<String> updateContractDetails(
            @PathVariable int hotelId,
            @RequestBody ContractDTO contractUpdateDTO)
    {
        hotelService.updateContractDetails(hotelId, contractUpdateDTO);
        return ResponseEntity.ok("Contract details updated successfully");
    }

    /**
     * Retrieves a list of expired hotels.
     *
     * @return Returns a ResponseEntity with a list of Hotel objects representing expired hotels and HttpStatus.OK status.
     */
    @GetMapping("/expired-hotels")
    public ResponseEntity<List<Hotel>> getExpiredHotels()
    {
        List<Hotel> expiredHotels = hotelService.getExpiredHotels();
        return new ResponseEntity<>(expiredHotels, HttpStatus.OK);
    }

}
