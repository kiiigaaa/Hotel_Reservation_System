/*
 * Copyright (c) 2023. CodeGen International (Pvt) Ltd. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of CodeGen
 * International (Pvt) Ltd. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with CodeGen International.
 *
 */
package com.example.booking_management_system.Utility;

import com.example.booking_management_system.entity.Hotel;
import com.example.booking_management_system.entity.Room;
import lombok.Data;

/**
 * <b>Utility class for calculating room price</b>
 * Description Text.
 *
 * @author kishen
 * @since 11 Aug 2023
 */
@Data
public class PriceCalculationUtility
{
    /**
     * Calculates the price of a room with markup based on the associated hotel's markup percentage.
     *
     * @param /room The Room object for which to calculate the price.
     * @return Returns the calculated price of the room including markup.
     */
    public double price( Room room)
    {
        double roomPrice = room.getPrice();// Get the base price of the room
        Hotel hotel = room.getHotel();// Get the associated hotel

        double markup = 0.0;
        markup = hotel.getMarkup()/100;// Get the markup percentage from the hotel

        return roomPrice + (roomPrice * markup);// Calculate the final price including markup
    }
}
