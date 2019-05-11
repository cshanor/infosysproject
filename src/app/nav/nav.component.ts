import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {

  constructor(private router: Router) { }

  username: any;

  ngOnInit() {
  }

  logoutFunction() {
    console.log('User logged out!');
    localStorage.clear();
    this.router.navigate(['/']);
  }

}
