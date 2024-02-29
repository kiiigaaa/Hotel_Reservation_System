import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HotelService } from './hotel.service';
import { Hotel } from 'src/app/models/hotel';
import { Contract } from 'src/app/models/contract';

describe('HotelService', () => {
  let service: HotelService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [HotelService]
    });
    service = TestBed.inject(HotelService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should send a POST request to add a hotel', () => {
    const dummyHotel: Hotel = {
      hotelId: 1,
      hotelName: 'Hilton',
      address: 'abcd',
      contact: '1234',
      contractStartDate:new Date('2023/01/01'),
      contractEndDate: new Date('2023/01/02'),
      markup: 15,
    };

    const expectedUrl = `${service.apiServerUrl}/saveHotel`;

    service.addHotel(dummyHotel).subscribe(hotel => {
      expect(hotel).toEqual(dummyHotel);
    });

    const req = httpMock.expectOne(expectedUrl);
    expect(req.request.method).toBe('POST');
    req.flush(dummyHotel);
  });

  it('should send a PATCH request to update a contract', () => {
    const dummyHotelId = 1; // Replace with a valid hotel ID
    const dummyContractData: Contract = {
      hotelId: 1,
    contractStartDate:new Date('2023/01/01'),
    contractEndDate: new Date('2023/01/02'),
    markup: 15,
    };

    const expectedUrl = `${service.apiServerUrl}/update-contract/${dummyHotelId}`;

    service.updateContract(dummyHotelId, dummyContractData).subscribe(() => {
      // Assertion or action after the request is completed
    });

    const req = httpMock.expectOne(expectedUrl);
    expect(req.request.method).toBe('PATCH');
    // No need to flush, as it's a PATCH request
  });

  it('should send a GET request to get expired hotels', () => {
    const dummyExpiredHotels: Hotel[] = [
      {
        hotelId: 1,
      hotelName: 'Hilton',
      address: 'abcd',
      contact: '1234',
      contractStartDate:new Date('2023/01/01'),
      contractEndDate: new Date('2023/01/02'),
      markup: 15,
      },
      {
        hotelId: 2,
       
      hotelName: 'Hilton',
      address: 'abcd',
      contact: '1234',
      contractStartDate:new Date('2023/01/01'),
      contractEndDate: new Date('2023/01/02'),
      markup: 15,
      }
      // ... add more dummy expired hotels
    ];

    const expectedUrl = `${service.apiServerUrl}/expired-hotels`;

    service.getExpiredHotels().subscribe(expiredHotels => {
      expect(expiredHotels).toEqual(dummyExpiredHotels);
    });

    const req = httpMock.expectOne(expectedUrl);
    expect(req.request.method).toBe('GET');
    req.flush(dummyExpiredHotels);
  });
});
