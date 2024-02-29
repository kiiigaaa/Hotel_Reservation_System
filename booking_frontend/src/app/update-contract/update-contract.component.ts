import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HotelService } from 'src/service/hotel.service';

@Component(
  {
  selector: 'app-update-contract',
  templateUrl: './update-contract.component.html',
  styleUrls: ['./update-contract.component.scss']
})
export class UpdateContractComponent implements OnInit {
  hotelId: number | null;
  contractForm: FormGroup;

  constructor(
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private hotelService: HotelService,
    private router: Router
  ) 
  {
    this.contractForm = this.formBuilder.group({
      contractStartDate: ['', Validators.required],
      contractEndDate: ['', Validators.required],
      markup: [0, Validators.required]
    });
    this.hotelId = null; // Initialize as null
  }

  ngOnInit(): void 
  {
    const hotelIdParam = this.route.snapshot.paramMap.get('hotelId');
    this.hotelId = hotelIdParam !== null ? +hotelIdParam : null; // Safely set hotelId
  }

  onSubmit(): void 
  {
    
    if (this.hotelId !== null && this.contractForm.valid) 
    {
      const contractData = this.contractForm.value;
      console.log(contractData)
      // Call your hotel service to update contract
      this.hotelService.updateContract(this.hotelId, contractData).subscribe(
        (updatedContract) => 
        {
          console.log('Contract updated successfully:', updatedContract);
          // Optionally, you can navigate back to the expired hotels list or perform other actions
          alert("Contract updated successfully!")
        },
        (error) => 
        {
          alert("Contract updated successfully!")
          this.router.navigate(['/expired-hotels'])
        }
      );
    }
  }
}
