
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { searchResults } from '../models/searchResults';
import { searchCriteria } from '../models/searchCriteria';
import { SearchService } from 'src/service/search.service';



@Component(
  {
  selector: 'app-room-search',
  templateUrl: './room-search.component.html',
  styleUrls: ['./room-search.component.scss']
})
export class RoomSearchComponent implements OnInit {
  searchCriteria: searchCriteria = 
  {
    checkInDate:new Date('2023/01/01'),
    numberOfNights: 0,
    hotelName:'',
    numberOfRooms:0,
    adults:0
  }
  searchResults: searchResults[] = [];
  currentPage: number = 1;
itemsPerPage: number = 3; // Adjust this value as needed


  constructor(private searchService: SearchService, private router: Router) {}

  ngOnInit() { }

  errorMessage: string = '';

  searchRooms() 
  {
    this.searchService.searchRooms(this.searchCriteria)
      .subscribe(
        (results: searchResults[]) => {
          this.searchResults = results;
         console.log(this.searchCriteria)
        },
        error => {
          console.error('Error searching rooms:', error);
          this.errorMessage = 'Please at least fill the cheking date and Hotel name!';
          alert(this.errorMessage); 
        }
      );
  }

  redirectToBooking(room: searchResults) 
  {
    this.router.navigate(['/booking', room.roomTypeId]);
  }

  getDisplayedResults(): searchResults[] 
  {
    const startIndex = (this.currentPage - 1) * this.itemsPerPage;
    const endIndex = startIndex + this.itemsPerPage;
    return this.searchResults.slice(startIndex, endIndex);
  }

  getPaginationRange(): number[] 
  {
    const totalPages = Math.ceil(this.searchResults.length / this.itemsPerPage);
    return Array.from({ length: totalPages }, (_, index) => index + 1);
  }

  setCurrentPage(page: number) 
  {
    this.currentPage = page;
  }
  
  
}
