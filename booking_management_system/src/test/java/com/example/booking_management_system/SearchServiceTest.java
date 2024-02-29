package com.example.booking_management_system;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <b>Description Title</b>
 * Description Text.
 *
 * @author kishen
 * @since 16 Aug 2023
 */
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.booking_management_system.Utility.ContractUtility;
import com.example.booking_management_system.Utility.PriceCalculationUtility;
import com.example.booking_management_system.dto.RoomDetailsDTO;
import com.example.booking_management_system.dto.SearchCriteriaDTO;
import com.example.booking_management_system.entity.Hotel;
import com.example.booking_management_system.entity.Room;
import com.example.booking_management_system.repo.BookingRepository;
import com.example.booking_management_system.repo.HotelRepository;
import com.example.booking_management_system.repo.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SearchServiceTest {

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private HotelRepository hotelRepository;

    @Mock
    private PriceCalculationUtility priceCalculationUtility;

    @InjectMocks
    private SearchService searchService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSearchAvailableRooms()
    {
        // Mocking data
        SearchCriteriaDTO searchCriteria = new SearchCriteriaDTO();
        searchCriteria.setCheckInDate(LocalDate.now());
        searchCriteria.setNumberOfNights(3);
        searchCriteria.setNumberOfRooms(2);
        searchCriteria.setHotelName("Hotel A");
        searchCriteria.setAdults(2);

        List<Object[]> queryResults = new ArrayList<>();
        Object[] result = {1, "Room Type A", 2, 100.0, BigDecimal.valueOf(5)};
        queryResults.add(result);

        Room room = new Room();
        room.setHotel(new Hotel());
        room.getHotel().setHotelId(1);
        when(bookingRepository.searchByDateRange(any(), any(), any())).thenReturn(queryResults);
        when(roomRepository.findById(1L)).thenReturn(java.util.Optional.of(room));
        when(hotelRepository.findById(1)).thenReturn(java.util.Optional.of(new Hotel()));
        when(priceCalculationUtility.price(any())).thenReturn(200.0);

        // Test the service method
        List<RoomDetailsDTO> roomDetailsDTOList = searchService.searchAvailableRooms(searchCriteria);

        // Assert the result
        assertNotNull(roomDetailsDTOList);
        assertEquals(1, roomDetailsDTOList.size());
        assertEquals("Room Type A", roomDetailsDTOList.get(0).getRoomTypeName());
        assertEquals(2, roomDetailsDTOList.get(0).getMaxAdults());
        assertEquals(200.0 * 2, roomDetailsDTOList.get(0).getPrice(), 0.01);
        assertEquals(BigDecimal.valueOf(5), roomDetailsDTOList.get(0).getAvailableRooms());

        verify(roomRepository, times(1)).findById(1L);
        verify(hotelRepository, times(1)).findById(1);
    }
}
