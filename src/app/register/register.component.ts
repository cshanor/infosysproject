import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

import {User} from "../User";
import {Router} from "@angular/router";
import {BackendService} from "../backend.service";
import {map} from "rxjs/operators";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;
  submitted = false;
  user = new User();
  public constructor(private formBuilder: FormBuilder, private backend: BackendService, private router: Router) { }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      firstname: ['', [Validators.required]],
      lastname: ['', [Validators.required]],
      username: ['', [Validators.required]],
      password: ['', [Validators.required, Validators.minLength(8)]]
    });
  }

  get f() {
    return this.registerForm.controls;
  }

  onSubmit() {
    console.log('button works');
    if (this.registerForm.invalid) {
      return;
    }

    this.user.id = 0;
    this.user.firstName = this.registerForm.value.firstname;
    this.user.lastName = this.registerForm.value.lastname;
    this.user.username = this.registerForm.value.username;
    this.user.password = this.registerForm.value.password;

    console.log(this.user);

    this.backend.addUser(JSON.stringify(this.user))
      .pipe(map(response => {

        /**
         * Check the response. If the response body was null, then user could not be registered.
         * Mostly caused by duplicate username, but could be anything.
         */
        if (response.body == null) {
          alert(['Username taken. Please choose another.']);
        } else {
          this.router.navigate(['/login']);
          this.submitted = true;
        }
      })).subscribe();
  }
}
