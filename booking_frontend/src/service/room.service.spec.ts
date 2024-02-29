import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { RoomService } from './room.service';
import { Room } from 'src/app/models/Room';

describe('RoomService', () => {
  let service: RoomService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [RoomService]
    });
    service = TestBed.inject(RoomService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should send a POST request to create a room', () => {
    const dummyHotelId = 1; // Replace with a valid hotel ID
    const dummyRoomData: Room = {
      
      roomTypeName: 'Standard Room',
      maxAdults: 2,
      price: 100,
      noOfRooms: 5,
      hotelId: dummyHotelId
      // Replace with a hotel object if needed
    };

    const expectedUrl = `${service.baseUrl}/createRoom/${dummyHotelId}`;

    service.createRoom(dummyHotelId, dummyRoomData).subscribe(room => {
      expect(room).toEqual(dummyRoomData);
    });

    const req = httpMock.expectOne(expectedUrl);
    expect(req.request.method).toBe('POST');
    req.flush(dummyRoomData);
  });
});
