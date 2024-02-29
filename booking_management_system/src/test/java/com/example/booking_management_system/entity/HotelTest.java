package com.example.booking_management_system.entity;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <b>Description Title</b>
 * Description Text.
 *
 * @author kishen
 * @since 16 Aug 2023
 */
import com.example.booking_management_system.entity.Hotel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HotelTest
{

 private Hotel hotel;

 @BeforeEach
 public void setUp() {
  hotel = new Hotel();
 }

 @Test
 public void testHotelId()
 {
  hotel.setHotelId(1);
  assertEquals(1, hotel.getHotelId());
 }

 @Test
 public void testHotelName()
 {
  hotel.setHotelName("Sample Hotel");
  assertEquals("Sample Hotel", hotel.getHotelName());
 }

 @Test
 public void testAddress()
 {
  hotel.setAddress("123 Main Street");
  assertEquals("123 Main Street", hotel.getAddress());
 }

 @Test
 public void testContact()
 {
  hotel.setContact("contact@example.com");
  assertEquals("contact@example.com", hotel.getContact());
 }

 @Test
 public void testContractStartDate()
 {
  LocalDate startDate = LocalDate.of(2023, 8, 1);
  hotel.setContractStartDate(startDate);
  assertEquals(startDate, hotel.getContractStartDate());
 }

 @Test
 public void testContractEndDate()
 {
  LocalDate endDate = LocalDate.of(2024, 7, 31);
  hotel.setContractEndDate(endDate);
  assertEquals(endDate, hotel.getContractEndDate());
 }

 @Test
 public void testMarkup()
 {
  hotel.setMarkup(10.0);
  assertEquals(10.0, hotel.getMarkup(), 0.001);
 }
}
