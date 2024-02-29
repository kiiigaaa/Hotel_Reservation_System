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

import com.example.booking_management_system.dto.RoomDetailsDTO;
import com.example.booking_management_system.dto.SearchCriteriaDTO;
import com.example.booking_management_system.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

/**
 * <b>controller class for search function</b>
 * This controller class handles requests related to searching for available rooms.
 * It provides an endpoint for searching available rooms based on specified criteria.
 * Utilizes the SearchService to perform the actual search.
 * @author kishen
 * @since 09 Aug 2023
 */


@RestController
@RequestMapping("/search")
public class SearchController
{
    @Autowired
    private SearchService searchService;

    /**
     * Searches for and displays available rooms based on specified search criteria.
     *
     * @param /checkInDate     The check-in date for the desired booking.
     * @param /numberOfNights  The number of nights for the desired booking.
     * @param /numberOfRooms   The number of rooms required for the booking.
     * @param /hotelName       The name of the hotel to search within.
     * @param /adults          The number of adults for the booking.
     * @return Returns a list of RoomDetailsDTO objects containing available room details.
     */
    @GetMapping("/rooms")
    public List<RoomDetailsDTO> searchAndDisplayAvailableRooms(@RequestParam LocalDate checkInDate,
                                                               @RequestParam int numberOfNights,
                                                               @RequestParam int numberOfRooms,
                                                               @RequestParam String hotelName,
                                                               @RequestParam int adults)
    {
        // Create a search criteria DTO from the request parameters
        SearchCriteriaDTO requestPayload = new SearchCriteriaDTO();
        requestPayload.setCheckInDate(checkInDate);
        requestPayload.setNumberOfNights(numberOfNights);
        requestPayload.setNumberOfRooms(numberOfRooms);
        requestPayload.setHotelName(hotelName);
        requestPayload.setAdults(adults);
        // Perform the search for available rooms using the SearchService
        return searchService.searchAvailableRooms(requestPayload);
    }

}


