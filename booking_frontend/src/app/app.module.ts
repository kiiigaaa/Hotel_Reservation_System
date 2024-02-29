import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CreateHotelComponent } from './create-hotel/create-hotel.component';
import { UpdateContractComponent } from './update-contract/update-contract.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AddRoomTypesComponent } from './add-room-types/add-room-types.component';
import { ExpiredHotelsComponent } from './expired-hotels/expired-hotels.component';
import { RoomSearchComponent } from './room-search/room-search.component';
import { BookingComponent } from './booking/booking.component';
import { ViewBookingsComponent } from './view-bookings/view-bookings.component';
import { MatCardModule } from '@angular/material/card';
import { HomeComponent } from './home/home.component';
import { ConfirmDialogComponent } from './confirm-dialog/confirm-dialog.component';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatDialogModule } from '@angular/material/dialog';

@NgModule({
  declarations: [
    AppComponent,
    CreateHotelComponent,
    UpdateContractComponent,
    AddRoomTypesComponent,
    ExpiredHotelsComponent,
    RoomSearchComponent,
    BookingComponent,
    ViewBookingsComponent,
    HomeComponent,
    ConfirmDialogComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule ,
    HttpClientModule,
    MatCardModule,
    MatSnackBarModule,
    MatDialogModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
