import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Booking } from '../models/booking';
import { BookingService } from 'src/service/booking.service';
import { Location } from '@angular/common';
import { MatSnackBar } from '@angular/material/snack-bar';


@Component(
  {
  selector: 'app-booking-form',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.scss']
})
export class BookingComponent implements OnInit 
{
  roomId!: number;
  booking: Booking = 
  {
    customerName :'',
    noOfRooms : 0,
    checkingDate:new Date('2023/01/01'),
    checkoutDate:new Date('2023/01/01'),
    contactNo:''
  }
  
  constructor(private location: Location, private route: ActivatedRoute, private bookingService: BookingService,private snackBar: MatSnackBar,) {}

  ngOnInit() 
  {
    this.route.params.subscribe((params: Params) => {
      this.roomId = +params['roomId'];
    });
    
  }

  bookRoom() 
  {
    this.bookingService.createBooking(this.roomId, this.booking)
      .subscribe(
        (response) => {
          console.log('Booking Response:', response);
          this.snackBar.open('Booking added successfully', 'Close', { duration: 3000 });
          if (response) 
          {
            console.log('Room booked successfully');
            this.snackBar.open('Booking added successfully', 'Close', { duration: 3000 });
          } else 
          {
            console.error('Room booking not successful. Response:', response);
            
          }
          
        }
        
      );
      this.snackBar.open('Booking added successfully', 'Close', { duration: 3000 });
      this.resetFormFields();
      
  }

  resetFormFields() 
  {
    this.booking = {
      customerName: '',
      noOfRooms: 0,
      checkingDate: new Date(),
      checkoutDate: new Date(),
      contactNo: ''
    };
  }
  
  
}
