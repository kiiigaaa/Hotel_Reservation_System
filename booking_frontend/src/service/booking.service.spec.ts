import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { BookingService } from './booking.service';
import { Booking } from 'src/app/models/booking';

describe('BookingService', () => {
  let service: BookingService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [BookingService]
    });
    service = TestBed.inject(BookingService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should send a POST request to create a booking', () => {
    const dummyRoomTypeId = 1; // Replace with a valid room type ID
    const dummyBookingData: Booking = {
      customerName: 'amal',
      noOfRooms: 2,
      checkingDate: new Date('2023/01/01'),
      checkoutDate: new Date('2023/01/02'),
      contactNo: '12345'
    };

    const expectedUrl = `${service.baseUrl}/${dummyRoomTypeId}`;

    service.createBooking(dummyRoomTypeId, dummyBookingData).subscribe(booking => {
      expect(booking).toEqual(dummyBookingData);
    });

    const req = httpMock.expectOne(expectedUrl);
    expect(req.request.method).toBe('POST');
    req.flush(dummyBookingData);
  });

  it('should send a GET request to get all bookings with details', () => {
    const dummyBookings: Booking[] = [
      {
        customerName: 'amal',
        noOfRooms: 2,
        checkingDate: new Date('2023/01/01'),
        checkoutDate: new Date('2023/01/02'),
        contactNo: '12345'
      },
      {
        customerName: 'nimal',
        noOfRooms: 2,
        checkingDate: new Date('2023/01/01'),
        checkoutDate: new Date('2023/01/02'),
        contactNo: '12345'
      }
      // ... add more dummy bookings
    ];

    const expectedUrl = `${service.baseUrl}/view`;

    service.getAllBookingsWithDetails().subscribe(bookings => {
      expect(bookings).toEqual(dummyBookings);
    });

    const req = httpMock.expectOne(expectedUrl);
    expect(req.request.method).toBe('GET');
    req.flush(dummyBookings);
  });

  it('should send a DELETE request to delete a booking', () => {
    const dummyBookingId = 1; // Replace with a valid booking ID

    const expectedUrl = `${service.baseUrl}/${dummyBookingId}`;

    service.deleteBooking(dummyBookingId).subscribe(() => {
      // Assertion or action after the request is completed
    });

    const req = httpMock.expectOne(expectedUrl);
    expect(req.request.method).toBe('DELETE');
    // No need to flush, as it's a DELETE request
  });
});
