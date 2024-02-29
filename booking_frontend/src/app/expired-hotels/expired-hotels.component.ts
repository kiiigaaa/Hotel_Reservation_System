// expired-hotels.component.ts
import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';
import { HotelService } from 'src/service/hotel.service';
import { Hotel } from '../models/hotel';

@Component({
  selector: 'app-expired-hotels',
  templateUrl: './expired-hotels.component.html',
  styleUrls: ['./expired-hotels.component.scss']
})
export class ExpiredHotelsComponent implements OnInit {
  currentPage = 1; // Current page number
  pageSize = 10;   // Number of items per page
  expiredHotels: Hotel[] = [];

  constructor(private hotelService: HotelService, private router: Router) { }

  ngOnInit(): void {
    this.hotelService.getExpiredHotels().subscribe(
      (hotels) => {
        this.expiredHotels = hotels;
      },
      (error) => {
        console.error('Error retrieving expired hotels:', error);
      }
    );
  }

  goToUpdateContract(hotelId: number): void {
    this.router.navigate(['/update-contract', hotelId]);
  }

  setCurrentPage(pageNumber: number) {
    this.currentPage = pageNumber;
  }
  
  get pagedExpiredHotels() {
    const startIndex = (this.currentPage - 1) * this.pageSize;
    const endIndex = startIndex + this.pageSize;
    return this.expiredHotels.slice(startIndex, endIndex);
  }
  
  get pages() {
    const pageCount = Math.ceil(this.expiredHotels.length / this.pageSize);
    return Array.from({ length: pageCount }, (_, index) => index + 1);
  }
  
}


