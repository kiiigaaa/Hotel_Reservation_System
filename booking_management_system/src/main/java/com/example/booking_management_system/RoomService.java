/*
 * Copyright (c) 2023. CodeGen International (Pvt) Ltd. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of CodeGen
 * International (Pvt) Ltd. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with CodeGen International.
 *
 */
package com.example.booking_management_system;

import com.example.booking_management_system.dto.RoomDTO;
import com.example.booking_management_system.entity.Hotel;
import com.example.booking_management_system.entity.Room;
import com.example.booking_management_system.repo.HotelRepository;
import com.example.booking_management_system.repo.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <b>Service layer of rooms</b>
 * services for creating, getting rooms
 *
 * @author kishen
 * @since 07 Aug 2023
 */

@Service
public class RoomService
{
    //calling repositories
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private HotelRepository hotelRepository;

    /*** Room type adding function
     * This retrieves room information from the user inputs and saves it
     *
     * @param /hotelid, room price,room type name, max adults, no of rooms
     * @return returns saved room repository
     */
    public Room createRoom( int hotelId, RoomDTO roomCreateDTO)
    {   // validate the hotel id
        Hotel hotel = hotelRepository.findById(hotelId)
                                     .orElseThrow(() -> new EntityNotFoundException("Hotel not found with ID: " + hotelId));
        //create new room object
        Room room = new Room();
        //set the user inputs to object
        room.setPrice(roomCreateDTO.getPrice());
        room.setRoomTypeName(roomCreateDTO.getRoomTypeName());
        room.setMaxAdults(roomCreateDTO.getMaxAdults());
        room.setHotel(hotel);
        room.setNoOfRooms(roomCreateDTO.getNoOfRooms());
        //save the room object from room repository
        return roomRepository.save(room);
    }

    /*** get all rooms by hotel id function
     * This retrieves room information from the database by the given hotel id
     *
     * @param /hotelid
     * @return returns room information
     */
    public List<Room> getAllRoomsByHotel( int hotelId)
    {
        //validate the hotel id
        Hotel hotel = hotelRepository.findById(hotelId)
                                     .orElseThrow(() -> new EntityNotFoundException("Hotel not found with ID: " + hotelId));
        //returns room information through room repository
        return roomRepository.findByHotel(hotel);
    }

    /*** get all rooms by room id function
     * This retrieves room information from the database by the given room id
     *
     * @param /room type id
     * @return returns room information
     */
    public Room getRoomById(int roomTypeId)
    {
        //validate the room id and returns it
        return roomRepository.findById( ( long ) roomTypeId )
                             .orElseThrow(() -> new EntityNotFoundException("Room type not found with ID: " + roomTypeId));
    }

}

