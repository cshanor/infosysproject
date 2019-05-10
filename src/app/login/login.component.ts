import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { BackendService } from "../backend.service";
import { User } from "../User";
import { map } from "rxjs/operators";
import { Router } from "@angular/router";
import { NavComponent } from "../nav/nav.component";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;

  user: User = {
    id: 0,
    firstName: '',
    lastName: '',
    username: '',
    password: ''
  };

  constructor(private formBuilder: FormBuilder,
              private backend: BackendService,
              private nav: NavComponent,
              private router: Router) {
    this.loginForm = this.formBuilder.group({
      'username': [this.user.username, [Validators.required]],
      'password': [this.user.password, [Validators.required, Validators.minLength(8)]]
    });
  }

  ngOnInit() {
  }

  onSubmit() {

    this.user.username = this.loginForm.value.username;
    this.user.password = this.loginForm.value.password;

    this.backend.authUser(JSON.stringify(this.user))
      .pipe(map(response => {
        if (response.body == null) {
          alert(['Placeholder alert']);
          console.log('in loginComponent.onSubmit(): response.body was null');
        } else {
          console.log('in loginComponent.onSubmit(): response.body was not null');

          localStorage.clear();
          localStorage.setItem('jwt', response.headers.get('Authorization'));
          localStorage.setItem('User_ID', response.headers.get('User_ID'));
          localStorage.setItem('Username', response.headers.get('Username'));
          this.router.navigate(['/info']);
        }
      })).subscribe();
  }

}
