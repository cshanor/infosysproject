import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators} from "@angular/forms";
import { BackendService } from "../backend.service";
import { Flight } from "../Flight";

import { map } from "rxjs/operators";
import { Router } from "@angular/router";

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent implements OnInit {
  reservationForm: FormGroup;
  submitted = false;
  flight = new Flight();
  seats = Array.from(Array(208), (_,x) => x);


  public constructor(private formBuilder: FormBuilder, private backend: BackendService, private router: Router) { }

  ngOnInit() {
    this.reservationForm = this.formBuilder.group({
      origin: ['', [Validators.required]],
      destination: ['', [Validators.required]],
      depart_date: ['', [Validators.required]],
      arrival_date: ['', [Validators.required]]
    });
  }

  get f() {
    return this.reservationForm.controls;
  }

  onSubmit() {

    console.log(this.seats);

    if (this.reservationForm.invalid) {
      return;
    }

    this.flight.flightNumber = 0;
    this.flight.origin = this.reservationForm.value.origin;
    this.flight.destination = this.reservationForm.value.destination;
    this.flight.departDate = this.reservationForm.value.depart_date;
    this.flight.arrivalTime = this.reservationForm.value.arrival_date;

    // this.backend.getFlight()
  }

}
