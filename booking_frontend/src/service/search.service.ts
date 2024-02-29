import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { searchCriteria } from 'src/app/models/searchCriteria'; // Adjust the import path
import { searchResults } from 'src/app/models/searchResults';

@Injectable
({
  providedIn: 'root'
})
export class SearchService {
  public baseUrl = 'http://localhost:8081/search/rooms';

  constructor(private http: HttpClient) {}

  searchRooms(searchCriteria: searchCriteria): Observable<searchResults[]> 
  {
    // Construct the query parameters
    let params = new HttpParams()
      .set('checkInDate', searchCriteria.checkInDate.toString())
      .set('numberOfNights', searchCriteria.numberOfNights.toString())
      .set('numberOfRooms', searchCriteria.numberOfRooms.toString())
      .set('hotelName', searchCriteria.hotelName.toString())
      .set('adults', searchCriteria.adults.toString());

    // Make the GET request with the constructed query parameters
    return this.http.get<searchResults[]>(this.baseUrl, { params });
  }



  }

