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

import com.example.booking_management_system.dto.RoomDTO;
import com.example.booking_management_system.entity.Room;
import com.example.booking_management_system.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <b>controller class for rooms</b>
 * This controller class handles requests related to managing rooms.
 * It provides endpoints for creating rooms and retrieving rooms by hotel ID.
 * Utilizes the RoomService to perform room-related operations.
 * @author kishen
 * @since 07 Aug 2023
 */
@RestController
@RequestMapping("/api/rooms")
public class RoomController
{
    @Autowired
    private RoomService roomService;

    /**
     * Creates a new room for a specific hotel.
     *
     * @param /hotelId       The ID of the hotel for which to create the room.
     * @param /roomCreateDTO The RoomDTO containing details for creating the room.
     * @return Returns a ResponseEntity with the created Room object and HttpStatus.CREATED status.
     */
    @PostMapping("createRoom/{hotelId}")
    public ResponseEntity<Room> createRoom(
            @PathVariable int hotelId,
            @RequestBody RoomDTO roomCreateDTO)
    {
        Room createdRoom = roomService.createRoom(hotelId, roomCreateDTO);
        return new ResponseEntity<>(createdRoom, HttpStatus.CREATED);
    }

    /**
     * Retrieves a list of rooms associated with a specific hotel.
     *
     * @param /hotelId The ID of the hotel for which to retrieve rooms.
     * @return Returns a ResponseEntity with a list of Room objects and HttpStatus.OK status.
     */
    @GetMapping("/{hotelId}")
    public ResponseEntity<List<Room>> getAllRoomsByHotel( @PathVariable int hotelId)
    {
        List<Room> rooms = roomService.getAllRoomsByHotel(hotelId);
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }


}

