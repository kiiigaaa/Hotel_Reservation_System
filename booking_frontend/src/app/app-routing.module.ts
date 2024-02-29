import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateHotelComponent } from './create-hotel/create-hotel.component';
import { AddRoomTypesComponent } from './add-room-types/add-room-types.component';
import { ExpiredHotelsComponent } from './expired-hotels/expired-hotels.component';
import { UpdateContractComponent } from './update-contract/update-contract.component';
import { RoomSearchComponent } from './room-search/room-search.component';
import { BookingComponent } from './booking/booking.component';
import { ViewBookingsComponent } from './view-bookings/view-bookings.component';
import { HomeComponent } from './home/home.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'add-contract', component: CreateHotelComponent },
  { path: 'add-room-types', component: AddRoomTypesComponent },
  { path: 'expired-hotels', component: ExpiredHotelsComponent },
  { path: 'update-contract/:hotelId', component: UpdateContractComponent },
  { path: 'search', component: RoomSearchComponent },
  { path: 'booking/:roomId', component: BookingComponent },
  { path: 'view-bookings', component: ViewBookingsComponent }
  // ... other routes if you have
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
