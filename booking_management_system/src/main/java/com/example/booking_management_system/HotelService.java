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

import com.example.booking_management_system.Exeptions.ContractExpiredException;
import com.example.booking_management_system.dto.ContractDTO;
import com.example.booking_management_system.dto.HotelDTO;
import com.example.booking_management_system.entity.Hotel;
import com.example.booking_management_system.repo.HotelRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <b>Service for hotel</b>
 * services for creating, getting, getting expired hotels
 *
 * @author kishen
 * @since 07 Aug 2023
 */
@Service
@Transactional
public class HotelService
{

    //calling repositories
    @Autowired
    public HotelService(HotelRepository hotelRepository, ModelMapper modelMapper)
    {
        this.hotelRepository = hotelRepository;
        this.modelMapper = modelMapper;
    }

    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;

    /*** Getting hotel by id function
     * This retrieves hotel information from the hotel id
     *
     * @param /hotelid
     * @return returns hotel object
     */
    public Hotel getHotelById(int hotelId)
    {
        //validating the id and retrieving hotel object
        Hotel hotel = hotelRepository.findById(hotelId)
                                     .orElseThrow(() -> new EntityNotFoundException("Hotel not found with ID: " + hotelId));

        // Checking if the contract for the hotel has expired
        if (contractHasExpired(hotel))
        {
            throw new ContractExpiredException("Hotel contract has expired.");
        }

        return hotel;
    }

    /**
     * Retrieves a list of expired hotels.
     *
     * @return Returns a list of expired hotel objects.
     */
    public List<Hotel> getExpiredHotels()
    {
        List<Hotel> allHotels = hotelRepository.findAll();
        LocalDate currentDate = LocalDate.now();

        List<Hotel> expiredHotels = allHotels.stream()
                                             .filter(hotel -> contractHasExpired(hotel))
                                             .collect( Collectors.toList());
        return expiredHotels;
    }


    /**
     * Checks if the contract for a given hotel has expired.
     *
     * @param /hotel The hotel for which to check the contract expiration.
     * @return Returns true if the contract has expired, false otherwise.
     */
    private boolean contractHasExpired(Hotel hotel)
    {
        LocalDate currentDate = LocalDate.now();
        return currentDate.isAfter(hotel.getContractEndDate());
    }


    /**
     * Updates the contract details for a hotel.
     *
     * @param /hotelId           The ID of the hotel to update.
     * @param /contractUpdateDTO The contract details to update.
     * @return Returns the updated hotel object.
     * @throws /EntityNotFoundException if the hotel with the given ID is not found.
     */
    public Hotel updateContractDetails(int hotelId, ContractDTO contractUpdateDTO)
    {
        Hotel hotel = hotelRepository.findById(hotelId)
                                     .orElseThrow(() -> new EntityNotFoundException("Hotel not found with ID: " + hotelId));

        modelMapper.map(contractUpdateDTO, hotel); // Map DTO fields to entity

        return hotelRepository.save(hotel);
    }

    /**
     * Creates a new hotel using the provided HotelDTO.
     *
     * @param /hotelDTO The HotelDTO containing information for the new hotel.
     * @return Returns the created hotel object.
     * @throws /IllegalArgumentException if the provided input is invalid.
     * @throws /RuntimeException if an error occurs during the creation process.
     */
    @Transactional
    public Hotel createHotel(HotelDTO hotelDTO)
    {
        try {
            validateHotelInput(hotelDTO);

            Hotel hotel = modelMapper.map(hotelDTO, Hotel.class);
            return hotelRepository.save(hotel);
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Invalid input: " + ex.getMessage());
        } catch (Exception ex) {
            throw new RuntimeException("An error occurred: " + ex.getMessage());
        }
    }

    /**
     * Validates the input fields of the HotelDTO.
     *
     * @param /hotelDTO The HotelDTO to validate.
     * @throws /IllegalArgumentException if any of the required fields are missing or invalid.
     */
    private void validateHotelInput(HotelDTO hotelDTO)
    {
        // Validation logic for hotel input fields
        if (hotelDTO.getHotelName() == null || hotelDTO.getHotelName().isEmpty())
        {
            throw new IllegalArgumentException("Hotel name is required");
        }

        if (hotelDTO.getAddress() == null || hotelDTO.getAddress().isEmpty())
        {
            throw new IllegalArgumentException("Address is required");
        }

        if (hotelDTO.getContact() == null || hotelDTO.getContact().isEmpty())
        {
            throw new IllegalArgumentException("Contact is required");
        }

        if (hotelDTO.getContractStartDate() == null || hotelDTO.getContractEndDate() == null ||
                    hotelDTO.getContractEndDate().isBefore(hotelDTO.getContractStartDate()))
        {
            throw new IllegalArgumentException("Invalid contract start or end dates");
        }

        if (hotelDTO.getMarkup() == null || hotelDTO.getMarkup() <= 0)
        {
            throw new IllegalArgumentException("Markup must be greater than 0");
        }
    }


}

