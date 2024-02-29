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
import java.util.List;

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
public class SearchCriteriaDTO
{
    private LocalDate checkInDate;
    private int numberOfNights;
    private String hotelName;
    private int numberOfRooms;
    private int adults;
}
