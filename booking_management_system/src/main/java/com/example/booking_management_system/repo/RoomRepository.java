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
import com.example.booking_management_system.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * <b>Repository interface for room </b>
 * This repository interface provides methods for querying and accessing Room entities in the database.* It extends JpaRepository to inherit common CRUD operations for Room entities.
 * Contains a custom query method for retrieving rooms based on the associated hotel.
 * Utilizes the Room and Hotel entities for queries.
 * @author kishen
 * @since 07 Aug 2023
 */
public interface RoomRepository extends JpaRepository<Room, Long>
{
    /**
     * Retrieves a list of rooms associated with a specific hotel.
     *
     * @param /hotel The Hotel entity for which to retrieve rooms.
     * @return Returns a list of Room entities associated with the given hotel.
     */
    List<Room> findByHotel( Hotel hotel);
}
