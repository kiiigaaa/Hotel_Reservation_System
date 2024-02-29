import { Component, OnInit } from '@angular/core';
import { HotelService } from 'src/service/hotel.service';
import { Hotel } from '../models/hotel';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-hotel',
  templateUrl: './create-hotel.component.html',
  styleUrls: ['./create-hotel.component.scss']
})
export class CreateHotelComponent implements OnInit {
  hotel: Hotel = {
    hotelId: 0,
    hotelName: '',
    address: '',
    contact: '',
    contractStartDate: new Date('2023/01/01'),
    contractEndDate: new Date('2023/01/01'),
    markup: 15
  };

  constructor(private router: Router, private hotelService: HotelService) {}

  ngOnInit() {}

  onSubmit() {
    this.hotelService.addHotel(this.hotel).subscribe(
      (createdHotel: Hotel) => {
        console.log('Hotel created:', createdHotel);
        const newHotelId = createdHotel.hotelId;
        console.log(newHotelId);
        this.router.navigate(['/add-room-types', { hotelId: newHotelId }]);
        alert('New contract added successfuly')
        // Optionally, you can navigate to a success page or update the UI
      },
      (error) => {
        console.error('Error creating hotel:', error);
        if (error.error && error.error.message) {
          alert(error.error.message); // Display the error message from the backend
        } else {
          alert('You must fill all the details'); // Generic error message
        }
      }
    );
  }
}
