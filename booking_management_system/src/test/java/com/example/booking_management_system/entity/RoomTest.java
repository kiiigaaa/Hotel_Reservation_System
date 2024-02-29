package com.example.booking_management_system.entity;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <b>Description Title</b>
 * Description Text.
 *
 * @author kishen
 * @since 16 Aug 2023
 */
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.example.booking_management_system.entity.Room;
import com.example.booking_management_system.entity.Hotel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class RoomTest
{

 @Mock
 private EntityManager entityManager;

 @Mock
 private TypedQuery<Room> query;

 private Room room;

 @BeforeEach
 public void setUp()
 {
  MockitoAnnotations.openMocks(this);
  room = new Room();
  room.setRoomTypeId(1);
  room.setRoomTypeName("Standard Room");
  room.setMaxAdults(2);
  room.setPrice(100.0);
  room.setNoOfRooms(10);

  Hotel hotel = new Hotel();
  hotel.setHotelId(1);
  room.setHotel(hotel);
 }

 @Test
 public void testRoomEntity()
 {
  assertEquals(1, room.getRoomTypeId());
  assertEquals("Standard Room", room.getRoomTypeName());
  assertEquals(2, room.getMaxAdults());
  assertEquals(100.0, room.getPrice());
  assertEquals(10, room.getNoOfRooms());
  assertEquals(1, room.getHotel().getHotelId());
 }

 @Test
 public void testFindAllRooms()
 {
  List<Room> rooms = new ArrayList<>();
  rooms.add(room);

  when(entityManager.createQuery("SELECT r FROM Room r", Room.class)).thenReturn(query);
  when(query.getResultList()).thenReturn(rooms);

  assertEquals(1, rooms.size());
 }
}
