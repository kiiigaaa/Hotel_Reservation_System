import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { SearchService } from './search.service';
import { searchCriteria } from 'src/app/models/searchCriteria'; // Adjust the import path
import { searchResults } from 'src/app/models/searchResults';

describe('SearchService', () => {
  let service: SearchService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [SearchService]
    });
    service = TestBed.inject(SearchService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should send a GET request with correct query parameters', () => {
    const dummySearchCriteria: searchCriteria = {
      checkInDate: new Date('2023/01/01'),
      numberOfNights: 3,
      numberOfRooms: 2,
      hotelName: 'Sample Hotel',
      adults: 2
    };

    const dummyResults: searchResults[] = [
      {
        roomTypeId: 1,
        roomTypeName: 'Standard Room',
        availableRooms: 5,
        price: 100,
        maxAdults: 2
        // Add other properties as needed
      },
      {
        roomTypeId: 2,
        roomTypeName: 'Deluxe Room',
        availableRooms: 3,
        price: 150,
        maxAdults: 3
        // Add other properties as needed
      }
      // ... add more dummy results
    ];

    service.searchRooms(dummySearchCriteria).subscribe(results => {
      expect(results).toEqual(dummyResults);
    });

    const req = httpMock.expectOne(`${service.baseUrl}?checkInDate=Sun%20Jan%2001%202023%2000:00:00%20GMT%2B0530%20(India%20Standard%20Time)&numberOfNights=3&numberOfRooms=2&hotelName=Sample%20Hotel&adults=2`);
    expect(req.request.method).toBe('GET');
    req.flush(dummyResults);
  });
});
