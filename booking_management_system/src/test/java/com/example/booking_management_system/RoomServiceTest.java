package com.example.booking_management_system;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <b>Description Title</b>
 * Description Text.
 *
 * @author kishen
 * @since 16 Aug 2023
 */
import com.example.booking_management_system.*;
import com.example.booking_management_system.dto.RoomDTO;
import com.example.booking_management_system.entity.Hotel;
import com.example.booking_management_system.entity.Room;
import com.example.booking_management_system.repo.HotelRepository;
import com.example.booking_management_system.repo.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RoomServiceTest
{

 @Mock
 private RoomRepository roomRepository;

 @Mock
 private HotelRepository hotelRepository;

 @InjectMocks
 private RoomService roomService;

 @BeforeEach
 public void setUp() {
  MockitoAnnotations.openMocks(this);
 }

 @Test
 public void testCreateRoom_ValidHotelIdAndRoomDTO()
 {
  int hotelId = 1;
  RoomDTO roomDTO = new RoomDTO();
  roomDTO.setPrice(100);
  roomDTO.setRoomTypeName("Standard Room");
  roomDTO.setMaxAdults(2);
  roomDTO.setNoOfRooms(10);

  Hotel mockHotel = new Hotel();

  when(hotelRepository.findById(anyInt())).thenReturn(Optional.of(mockHotel));

  Room result = roomService.createRoom(hotelId, roomDTO);

//  assertNotNull(result);
//  assertEquals(roomDTO.getPrice(), result.getPrice());
//  assertEquals(roomDTO.getRoomTypeName(), result.getRoomTypeName());
//  assertEquals(roomDTO.getMaxAdults(), result.getMaxAdults());
//  assertEquals(mockHotel, result.getHotel());
//  assertEquals(roomDTO.getNoOfRooms(), result.getNoOfRooms());
 }

 @Test
 public void testGetAllRoomsByHotel_ValidHotelId()
 {
  int hotelId = 1;
  Hotel mockHotel = new Hotel();
  mockHotel.setHotelId(hotelId);

  List<Room> mockRooms = new ArrayList<>();
  mockRooms.add(new Room());
  mockRooms.add(new Room());

  when(hotelRepository.findById(anyInt())).thenReturn(Optional.of(mockHotel));
  when(roomRepository.findByHotel(mockHotel)).thenReturn(mockRooms);

  List<Room> result = roomService.getAllRoomsByHotel(hotelId);

  assertEquals(mockRooms.size(), result.size());
 }

 @Test
 public void testGetRoomById_ValidRoomTypeId()
 {
  long roomTypeId = 1;
  Room mockRoom = new Room();

  when(roomRepository.findById(roomTypeId)).thenReturn(Optional.of(mockRoom));

  Room result = roomService.getRoomById((int) roomTypeId);

  assertEquals(mockRoom, result);
 }

 @Test
 public void testGetRoomById_RoomTypeNotFound()
 {
  long roomTypeId = 1;

  when(roomRepository.findById(roomTypeId)).thenReturn(Optional.empty());

  assertThrows(EntityNotFoundException.class, () -> roomService.getRoomById((int) roomTypeId));
 }

 // Add more test methods for other scenarios

}
