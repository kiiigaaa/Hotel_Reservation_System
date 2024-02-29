/*
 * Copyright (c) 2023. CodeGen International (Pvt) Ltd. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of CodeGen
 * International (Pvt) Ltd. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with CodeGen International.
 *
 */
package com.example.booking_management_system.repo;

import com.example.booking_management_system.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <b>Description Title</b>
 * Description Text.
 *
 * @author kishen
 * @since 06 Aug 2023
 */
public interface HotelRepository extends JpaRepository<Hotel, Integer>
{
}

