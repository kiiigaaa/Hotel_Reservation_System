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

import com.example.booking_management_system.Utility.ContractUtility;
import com.example.booking_management_system.dto.BookingDTO;
import com.example.booking_management_system.dto.ContractDTO;
import com.example.booking_management_system.dto.ViewBookingDTO;
import com.example.booking_management_system.entity.Booking;
import com.example.booking_management_system.entity.Hotel;
import com.example.booking_management_system.entity.Room;
import com.example.booking_management_system.repo.BookingRepository;
import com.example.booking_management_system.repo.HotelRepository;
import com.example.booking_management_system.repo.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * <b>Services for booking</b>
 * This class provides services related to booking management, including booking creation, retrieval, and deletion.
 * Also handles validation and connection with other entities.
 * @author kishen
 * @since 08 Aug 2023
 */
@Service
public class BookingService
{
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Creates a booking for a specified room type.
     *
     * @param roomTypeId The ID of the room type for which the booking is created.
     * @param bookingDTO The BookingDTO containing booking details.
     * @return Returns a message indicating the result of the booking creation process.
     */
    @Transactional
    public String createBooking(int roomTypeId, BookingDTO bookingDTO)
    {
        try
        {
            // Find the specified room type
            Room room = roomRepository.findById( ( long ) roomTypeId ).orElseThrow( () -> new EntityNotFoundException( "Room not found" ) );
            // Get the hotel ID associated with the room
            int hotelId = room.getHotel().getHotelId();
            // Find the hotel using the hotel ID
            Hotel hotel = hotelRepository.findById( hotelId ).orElseThrow( () -> new EntityNotFoundException( "" ) );
            // Check if the hotel's contract has expired
            if( ContractUtility.hasExpired( hotel ) )
            {
                return "Hotel Contract is Expired";
            }
            else
            {   // Validate booking input
                if( bookingDTO.getCustomerName() == null || bookingDTO.getCustomerName().isEmpty() )
                {
                    throw new IllegalArgumentException( "Customer name is required" );
                }

                if( bookingDTO.getNoOfRooms() <= 0 )
                {
                    throw new IllegalArgumentException( "Number of rooms must be greater than 0" );
                }

                LocalDate checkingDate = bookingDTO.getCheckingDate();
                LocalDate checkoutDate = bookingDTO.getCheckoutDate();
                // Create a new Booking object from the BookingDTO
                Booking booking = modelMapper.map( bookingDTO, Booking.class );
                booking.setRoomType( room );
                // Validate checking and checkout dates
                if( checkingDate == null || checkoutDate == null || checkoutDate.isBefore( checkingDate ) )
                {
                    throw new IllegalArgumentException( "Invalid checking or checkout dates" );
                }

                // Save the booking
                bookingRepository.save( booking );

                return "booking";
            }
        }catch (EntityNotFoundException ex) {
            return "Entity not found: " + ex.getMessage();
        } catch (IllegalArgumentException ex) {
            return "Invalid input: " + ex.getMessage();
        } catch (Exception ex) {
            return "An error occurred: " + ex.getMessage();
        }
    }

    /**
     * Retrieves a list of ViewBookingDTO objects containing details of all bookings.
     *
     * @return Returns a list of ViewBookingDTO objects.
     */
    public List<ViewBookingDTO> getAllBookingsWithDetails() {
        List<Booking> bookings = bookingRepository.findAll();
        List<ViewBookingDTO> viewBookings = new ArrayList<>();
        // Map booking details to ViewBookingDTO objects
        for (Booking booking : bookings) {
            ViewBookingDTO viewBookingDTO = modelMapper.map(booking, ViewBookingDTO.class);
            viewBookingDTO.setHotelName(booking.getRoomType().getHotel().getHotelName());
            viewBookingDTO.setRoomTypeName(booking.getRoomType().getRoomTypeName());
            viewBookings.add(viewBookingDTO);
        }

        return viewBookings;
    }

    /**
     * Deletes a booking based on the booking ID.
     *
     * @param /bookingId The ID of the booking to delete.
     */
    @Transactional
    public void deleteBooking(int bookingId) {
        bookingRepository.deleteById(bookingId);
    }



}
