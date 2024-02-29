package com.example.booking_management_system.entity;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <b>Description Title</b>
 * Description Text.
 *
 * @author kishen
 * @since 16 Aug 2023
 */
import com.example.booking_management_system.entity.Booking;
import com.example.booking_management_system.entity.Room;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookingTest
{

 private Booking booking;

 @BeforeEach
 public void setUp() {
  booking = new Booking();
 }

 @Test
 public void testBookingId()
 {
  booking.setBookingId(1);
  assertEquals(1, booking.getBookingId());
 }

 @Test
 public void testRoomType()
 {
  Room roomType = new Room();
  booking.setRoomType(roomType);
  assertEquals(roomType, booking.getRoomType());
 }

 @Test
 public void testCustomerName()
 {
  booking.setCustomerName("John Doe");
  assertEquals("John Doe", booking.getCustomerName());
 }

 @Test
 public void testNoOfRooms()
 {
  booking.setNoOfRooms(2);
  assertEquals(2, booking.getNoOfRooms());
 }

 @Test
 public void testCheckingDate()
 {
  LocalDate checkingDate = LocalDate.of(2023, 8, 15);
  booking.setCheckingDate(checkingDate);
  assertEquals(checkingDate, booking.getCheckingDate());
 }

 @Test
 public void testCheckoutDate()
 {
  LocalDate checkoutDate = LocalDate.of(2023, 8, 20);
  booking.setCheckoutDate(checkoutDate);
  assertEquals(checkoutDate, booking.getCheckoutDate());
 }

 @Test
 public void testContactNo()
 {
  booking.setContactNo("1234567890");
  assertEquals("1234567890", booking.getContactNo());
 }
}
