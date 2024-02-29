import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Hotel } from 'src/app/models/hotel';
import { Observable } from 'rxjs';
import { Contract } from 'src/app/models/contract';

@Injectable
({
  providedIn: 'root'
})
export class HotelService 
{

  public apiServerUrl = 'http://localhost:8081/api/hotels';

  constructor(private http: HttpClient) { }

  // getHotels(): Observable<Hotel[]>{
  //   return this.http.get<Hotel[]>('${this.apiServerUrl}/hotel/all');
  // }

   // This method sends a POST request to add a new hotel.
  // It takes a Hotel object as a parameter and returns an Observable of type Hotel.
  addHotel(hotel: Hotel): Observable<Hotel>
  {
    return this.http.post<Hotel>(`${this.apiServerUrl}/saveHotel`,hotel);
  }

   // This method sends a PATCH request to update the contract information of a hotel.
  // It takes the hotelId and contractData as parameters and does not return a value.
  updateContract(hotelId: number, contractData: any) 
  {
    return this.http.patch(`${this.apiServerUrl}/update-contract/${hotelId}`, contractData);
  }

  // This method sends a GET request to retrieve a list of expired hotels.
  // It returns an Observable containing an array of Hotel objects.
  getExpiredHotels(): Observable<Hotel[]>
  {
    return this.http.get<Hotel[]>(`${this.apiServerUrl}/expired-hotels`)
  }

}
