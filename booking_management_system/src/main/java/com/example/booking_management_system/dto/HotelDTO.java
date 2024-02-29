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
 * @since 06 Aug 2023
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HotelDTO
{
    private int hotelId;
    private String hotelName;
    private String address;
    private String contact;
    private LocalDate contractStartDate;
    private LocalDate contractEndDate;
    private Double markup;
}
