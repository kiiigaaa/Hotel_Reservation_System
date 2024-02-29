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
import com.example.booking_management_system.Utility.PriceCalculationUtility;
import com.example.booking_management_system.dto.RoomDetailsDTO;
import com.example.booking_management_system.dto.SearchCriteriaDTO;
import com.example.booking_management_system.entity.Hotel;
import com.example.booking_management_system.entity.Room;
import com.example.booking_management_system.repo.BookingRepository;
import com.example.booking_management_system.repo.HotelRepository;
import com.example.booking_management_system.repo.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * <b>Service layer of search function</b>
 * This is the service layer of the search for available rooms and prices for a certain hotel
 *
 * @author kishen
 * @since 09 Aug 2023
 */
@Service
public class SearchService
{
    //calling the needed repositories
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private HotelRepository hotelRepository;

    /*** Complete search function
     * This retrieves the search criteria from the user inputs and returns the available rooms and details
     *
     * @param /Check in date,number of nights, hotel name, number of adults, number of rooms
     * @return returns search results such as room type,calculated total price, max adults and available rooms
     */
    public List<RoomDetailsDTO> searchAvailableRooms( SearchCriteriaDTO searchCriteria )
    {
        //validating the search criteria
        if (searchCriteria == null)
        {
            throw new IllegalArgumentException("Search criteria cannot be null");
        }

        //assigning to local variables
        LocalDate checkInDate = searchCriteria.getCheckInDate();
        LocalDate checkOutDate = checkInDate.plusDays( searchCriteria.getNumberOfNights() );
        int numberOfRooms = searchCriteria.getNumberOfRooms();
        String hotelName = searchCriteria.getHotelName();
        int adults = searchCriteria.getAdults();

        //validating if user inputs are null
        if (checkInDate == null || checkOutDate == null || numberOfRooms < 0 || adults < 0)
        {
            throw new IllegalArgumentException("Invalid search criteria");
        }

        //create an arraylist of RoomDetailsDTO
        List<RoomDetailsDTO> roomDetailsDTOList = new ArrayList<>();

        //get the results from bookingRepository query by giving parameters
        List<Object[]> queryResults = bookingRepository.searchByDateRange(checkInDate, checkOutDate,hotelName);

        //run a for loop to retrieve results
        for (Object[] result : queryResults)
        {
            int roomTypeId = (int) result[0]; //assign  results to variables
            String roomTypeName = (String) result[1];
            int maxAdults = (int) result[2];
            double roomPrice =  (double) result[3];
            BigDecimal availableRooms = (BigDecimal ) result[4];

            //call the room repository to get the room id if the id is invalid throw an exception
            Room room = roomRepository.findById( ( long ) roomTypeId ).orElseThrow(() -> new EntityNotFoundException("Room not found"));

            //get hotel id
            int hotelId = room.getHotel().getHotelId();

            //validate the hotel id
            Hotel hotel = hotelRepository.findById( hotelId ).orElseThrow(() -> new EntityNotFoundException(""));

            //call price calculation
            PriceCalculationUtility priceCalculation = new PriceCalculationUtility();

            //calculate the price by number of rooms
            double price = priceCalculation.price( room ) * numberOfRooms;

            //validate the contract expiration
            if(!ContractUtility.hasExpired(hotel))
            {
            //validate if inputted number of rooms are lower to available rooms and adults are lower to max adults available for a room
            if(numberOfRooms<=availableRooms.intValue() && adults <= maxAdults)
            {
                //add the room details to the list
                RoomDetailsDTO roomDetailsDTO = new RoomDetailsDTO( roomTypeId, roomTypeName, maxAdults, price, availableRooms );
                roomDetailsDTOList.add( roomDetailsDTO );
            }}
        }

        return roomDetailsDTOList;

    }

}