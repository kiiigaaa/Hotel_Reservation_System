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

import com.example.booking_management_system.dto.RoomDetailsDTO;
import com.example.booking_management_system.entity.Booking;
import com.example.booking_management_system.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

/**
 * <b>Repository interface for booking</b>
 * This repository interface provides methods for querying and accessing Booking entities in the database.
 * It extends JpaRepository to inherit common CRUD operations for Booking entities.
 * Contains custom query methods for retrieving booking information based on specified criteria.
 * Utilizes the Booking and Room entities for queries.
 * @author kishen
 * @since 06 Aug 2023
 */
public interface BookingRepository extends JpaRepository<Booking, Integer>
{

    /**
     * Searches for available rooms within a specified date range and hotel name.
     *
     * @param /checkInDate  The check-in date of the desired booking.
     * @param /checkOutDate The check-out date of the desired booking.
     * @param /hotelName    The name of the hotel for which to search for available rooms.
     * @return Returns a list of Object arrays containing room details.
     */
    @Query(value = "SELECT r.room_type_id, r.room_type_name, r.max_adults, r.price, r.no_of_rooms - COALESCE(SUM(b.no_of_rooms), 0) AS availableRooms, h.hotel_name " +
                           "FROM sun_db.room r " +
                           "LEFT JOIN sun_db.booking b ON r.room_type_id = b.room_type_id " +
                           "AND (b.checking_date BETWEEN ?1 AND ?2 " +
                           "OR b.checkout_date BETWEEN ?1 AND ?2 " +
                           "OR (b.checking_date <= ?1 AND b.checkout_date >= ?2)) " +
                           "LEFT JOIN sun_db.hotel h ON r.hotel_id = h.hotel_id " +
                           "WHERE h.hotel_name LIKE %?3% " +
                           "GROUP BY r.room_type_id, r.room_type_name, r.max_adults, r.price, r.no_of_rooms, h.hotel_name", nativeQuery = true)
    List<Object[]> searchByDateRange(LocalDate checkInDate, LocalDate checkOutDate, String hotelName);


    @Modifying
    @Query(value="DELETE FROM sun_db.booking b WHERE b.booking_id = ?1", nativeQuery = true)
    void deleteById(int bookingId);
//    @Query(value="select b.room_type_id from sun_db.booking b,sun_db.hotel h,sun_db.room r where r.room_type_id = b.room_type_id and h.hotel_id = r.hotel_id and h.hotel_name = 'Cinnamon'")

//    @Query(value = "SELECT r.room_type_id, r.room_type_name, r.max_adults, r.price, r.no_of_rooms - COALESCE(SUM(b.no_of_rooms), 0) AS availableRooms FROM Room r LEFT JOIN Booking b ON r.room_type_id = b.room_type_id AND (b.checking_date BETWEEN ?1 AND ?2 OR b.checkout_date BETWEEN ?1 AND ?2 OR (b.checking_date <= ?1 AND b.checkout_date >= ?2)) GROUP BY r.room_type_id, r.room_type_name, r.max_adults, r.price, r.no_of_rooms" ,nativeQuery = true)
//    List<Object[]> searchByDateRange( LocalDate checkInDate, LocalDate checkOutDate );

}
