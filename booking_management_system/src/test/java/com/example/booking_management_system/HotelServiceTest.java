package com.example.booking_management_system;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <b>Description Title</b>
 * Description Text.
 *
 * @author kishen
 * @since 16 Aug 2023
 */
import static org.mockito.Mockito.*;

import com.example.booking_management_system.HotelService;
import com.example.booking_management_system.dto.ContractDTO;
import com.example.booking_management_system.dto.HotelDTO;
import com.example.booking_management_system.entity.Hotel;
import com.example.booking_management_system.repo.HotelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import com.example.booking_management_system.*;
import com.example.booking_management_system.Exeptions.ContractExpiredException;
import com.example.booking_management_system.dto.ContractDTO;
import com.example.booking_management_system.dto.HotelDTO;
import com.example.booking_management_system.entity.Hotel;
import com.example.booking_management_system.repo.HotelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HotelServiceTest
{

 @Mock
 private HotelRepository hotelRepository;

 @Mock
 private ModelMapper modelMapper;

 @InjectMocks
 private HotelService hotelService;

 @BeforeEach
 public void setUp()
 {
  MockitoAnnotations.openMocks(this);
 }

 @Test
 public void testGetHotelById_ValidHotelId()
 {
  Hotel mockHotel = new Hotel();
  mockHotel.setContractEndDate(LocalDate.now().plusDays(1)); // Future date, contract not expired

  when(hotelRepository.findById(anyInt())).thenReturn(Optional.of(mockHotel));

  Hotel result = hotelService.getHotelById(1);

  assertEquals(mockHotel, result);
 }

 @Test
 public void testGetExpiredHotels_NoExpiredHotels()
 {
  Hotel notExpiredHotel = new Hotel();
  notExpiredHotel.setContractEndDate(LocalDate.now().plusDays(1)); // Future date, contract not expired

  List<Hotel> mockHotels = new ArrayList<>();
  mockHotels.add(notExpiredHotel);

  when(hotelRepository.findAll()).thenReturn(mockHotels);

  List<Hotel> expiredHotels = hotelService.getExpiredHotels();

  assertTrue(expiredHotels.isEmpty());
 }

 @Test
 public void testUpdateContractDetails_ValidHotelIdAndContractDTO()
 {
  Hotel mockHotel = new Hotel();

  when(hotelRepository.findById(anyInt())).thenReturn(Optional.of(mockHotel));
  when(hotelRepository.save(any(Hotel.class))).thenReturn(mockHotel);

  ContractDTO contractDTO = new ContractDTO();
  Hotel result = hotelService.updateContractDetails(1, contractDTO);

  assertEquals(mockHotel, result);
  verify(modelMapper).map(contractDTO, mockHotel);
 }

 @Test
 public void testCreateHotel_ValidHotelDTO()
 {
  HotelDTO hotelDTO = new HotelDTO();
  hotelDTO.setHotelName("Sample Hotel");
  hotelDTO.setAddress("123 Main St");
  hotelDTO.setContact("contact@example.com");
  hotelDTO.setContractStartDate(LocalDate.now());
  hotelDTO.setContractEndDate(LocalDate.now().plusDays(7));
  hotelDTO.setMarkup(10.0);

  Hotel mockHotel = new Hotel();

  when(modelMapper.map(hotelDTO, Hotel.class)).thenReturn(mockHotel);
  when(hotelRepository.save(any(Hotel.class))).thenReturn(mockHotel);

  Hotel result = hotelService.createHotel(hotelDTO);

  assertEquals(mockHotel, result);
  verify(modelMapper).map(hotelDTO, Hotel.class);
 }

 // Add more test methods for other scenarios

 @Test
 public void testGetHotelById_ExpiredContract()
 {
  Hotel expiredHotel = new Hotel();
  expiredHotel.setContractEndDate(LocalDate.now().minusDays(1)); // Past date, contract expired

  when(hotelRepository.findById(anyInt())).thenReturn(Optional.of(expiredHotel));

  assertThrows(ContractExpiredException.class, () -> hotelService.getHotelById(1));
 }

 @Test
 public void testCreateHotel_InvalidHotelDTO()
 {
  HotelDTO invalidHotelDTO = new HotelDTO(); // Incomplete data

  assertThrows(IllegalArgumentException.class, () -> hotelService.createHotel(invalidHotelDTO));
 }

 // ...
}
