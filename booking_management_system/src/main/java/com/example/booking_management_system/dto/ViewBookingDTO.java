/*
 * Copyright (c) 2023. CodeGen International (Pvt) Ltd. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of CodeGen
 * International (Pvt) Ltd. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with CodeGen International.
 *
 */
package com.example.booking_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * <b>Description Title</b>
 * Description Text.
 *
 * @author kishen
 * @since 15 Aug 2023
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ViewBookingDTO
{
    private int bookingId;
    private String hotelName;
    private String roomTypeName;
    private String customerName;
    private int noOfRooms;
    private LocalDate checkingDate;
    private LocalDate checkoutDate;
    private String contactNo;
}
