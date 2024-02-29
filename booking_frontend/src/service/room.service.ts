// room.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Room } from 'src/app/models/Room';


@Injectable
({
  providedIn: 'root'
})
export class RoomService
 {
  public baseUrl = 'http://localhost:8081/api/rooms'; 

  constructor(private http: HttpClient) {}

  // This method sends a POST request to create a new room for a specific hotel.
  // It takes the hotelId and roomData as parameters and returns an Observable of type Room.
  createRoom(hotelId: number, roomData: Room): Observable<Room>
   {
    const url = `${this.baseUrl}/createRoom/${hotelId}`;
    return this.http.post<Room>(url, roomData);
  }
}
