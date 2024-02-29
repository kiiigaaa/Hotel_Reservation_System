import { Component } from '@angular/core';
import { Hotel } from './models/hotel';
import { HttpClient } from '@angular/common/http';
import { HotelService } from 'src/service/hotel.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'booking_frontend';

  public hotel!: Hotel[];

  constructor(private hotelService: HotelService) { }
}
