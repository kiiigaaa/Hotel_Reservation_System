import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Booking } from 'src/app/models/booking';

@Injectable(
  {
  providedIn: 'root'
})
export class BookingService 
{
  public baseUrl = 'http://localhost:8081/api/booking';

  constructor(private http: HttpClient) {}


  // This method sends a POST request to create a new booking for a specific room type.
  // It takes the roomTypeId and bookingData as parameters and returns an Observable of type Booking.
  createBooking(roomTypeId: number, bookingData: Booking): Observable<Booking> 
  {
    const url = `${this.baseUrl}/${roomTypeId}`;
    console.log(bookingData)
    return this.http.post<Booking>(url, bookingData);
  }

   // This method sends a GET request to retrieve all bookings with their details.
   // It returns an Observable containing an array of Booking objects.
  getAllBookingsWithDetails(): Observable<Booking[]> 
  {
    return this.http.get<Booking[]>(this.baseUrl + '/view');
  }

   // This method sends a DELETE request to delete a booking by its bookingId.
   // It takes the bookingId as a parameter and returns an Observable of type void.
  deleteBooking(bookingId: number): Observable<void> 
  {
    const url = `${this.baseUrl}/${bookingId}`;
    return this.http.delete<void>(url);
  }
}
