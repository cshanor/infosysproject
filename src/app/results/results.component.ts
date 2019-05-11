import { Component, OnInit } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { BackendService } from "../backend.service";

@Component({
  selector: 'app-results',
  templateUrl: './results.component.html',
  styleUrls: ['./results.component.css']
})
export class ResultsComponent implements OnInit {

  search = '';
  results: Object;

  constructor(private http: HttpClient, private backend: BackendService) { }

  ngOnInit() {
  }

  getFlights() {
    this.backend.getFlights();
  }

  // searchClick() {
  //   this.getFlights().subscribe(data => {
  //     this.results = data;
  //     console.log(this.results);
  //   })
  // }
}
