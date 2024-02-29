import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RoomService } from 'src/service/room.service';



@Component({
  selector: 'app-add-room-types',
  templateUrl: './add-room-types.component.html',
  styleUrls: ['./add-room-types.component.scss']
})
export class AddRoomTypesComponent implements OnInit {
  roomForm!: FormGroup;
  hotelId!: number;

  constructor(
    private formBuilder: FormBuilder,
    private roomService: RoomService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.initForm();
    this.getHotelIdFromRoute();
  }

  private initForm(): void {
    this.roomForm = this.formBuilder.group({
      roomTypeName: ['', Validators.required],
      maxAdults: ['', Validators.required],
    price: ['', Validators.required],
    noOfRooms: ['', Validators.required],
      // Add more form controls as needed
    });
  }

  private getHotelIdFromRoute(): void {
    const paramMap = this.route.snapshot.paramMap;
    const hotelIdParam = paramMap.get('hotelId');
    if (hotelIdParam !== null) {
      this.hotelId = +hotelIdParam;
    }
  }

  onSubmit(): void {
    if (this.hotelId !== null && this.roomForm.valid) {
      const roomData = this.roomForm.value;
      this.roomService.createRoom(this.hotelId, roomData).subscribe(
        (createdRoom) => {
          console.log('Room created successfully:', createdRoom);
          // Clear the form or perform other actions
          alert('Room created successfully!')
          this.roomForm.reset(); 
        },
        (error) => {
          console.error('Error creating room:', error);
        }
      );
    }
  }
}
