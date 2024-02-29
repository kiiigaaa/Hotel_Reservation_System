/*
 * Copyright (c) 2023. CodeGen International (Pvt) Ltd. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of CodeGen
 * International (Pvt) Ltd. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with CodeGen International.
 *
 */
package com.example.booking_management_system.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <b>Description Title</b>
 * Description Text.
 *
 * @author kishen
 * @since 05 Aug 2023
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name= "Booking")
public class Booking
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roomTypeId")
    private Room roomType;
    private String customerName;
    private int noOfRooms;
    private LocalDate checkingDate;
    private LocalDate checkoutDate;
    private String contactNo;

}