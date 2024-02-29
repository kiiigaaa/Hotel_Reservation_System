import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BookingService } from 'src/service/booking.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmDialogComponent } from '../confirm-dialog/confirm-dialog.component';



@Component(
  {
  selector: 'app-view-bookings',
  templateUrl: './view-bookings.component.html',
  styleUrls: ['./view-bookings.component.scss']
})
export class ViewBookingsComponent implements OnInit {
  bookings: any[] = [];
  currentPage: number = 1;
  itemsPerPage: number = 6; // Number of items to display per page
  totalItems: number = 0;


  constructor(
    private bookingService: BookingService,
    private activatedRoute: ActivatedRoute,
    private snackBar: MatSnackBar,
    private dialog: MatDialog
    
  ) {}

  ngOnInit() 
  {
    this.loadBookings();
  }

  // Load bookings by subscribing to the booking service's getAllBookingsWithDetails method.
  loadBookings() 
  {
    this.bookingService.getAllBookingsWithDetails().subscribe(
      (data) => {
        this.bookings = data;
      },
      (error) => {
        console.error('Error loading bookings:', error);
        alert('Error while loading Bookings!');
      }
    );
  }

  // Handler for page change event
  onPageChange(pageNumber: number) 
  {
    this.currentPage = pageNumber;
  }

  // Get the subset of bookings to display on the current page.
  getDisplayedBookings() 
  {
    const startIndex = (this.currentPage - 1) * this.itemsPerPage;
    return this.bookings.slice(startIndex, startIndex + this.itemsPerPage);
  }

  
  // Cancel a booking by calling the booking service's deleteBooking method.
  cancelBooking(bookingId: number) 
  {
    this.bookingService.deleteBooking(bookingId).subscribe(
      () => {
        console.log('Booking canceled successfully');
         // Reload bookings after canceling
         this.loadBookings();
         this.snackBar.open('Booking canceled successfully', 'Close', { duration: 3000 });
      },
      (error) => {
        console.error('Error canceling booking:', error);
        this.snackBar.open('Unable to cancel the booking', 'Close', { duration: 3000, panelClass: 'error-snackbar' });
        this.loadBookings();
      }
      
    );
  }

  // Open a confirmation dialog before canceling a booking.
  confirmCancellation(bookingId: number) {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      width: '300px',
      data: {
        title: 'Confirm Cancellation',
        message: 'Are you sure you want to cancel this booking?'
      }
    });
  
    // Subscribe to the dialog's afterClosed event to perform action based on the result.
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.cancelBooking(bookingId);
      }
    });
  }
}
 //