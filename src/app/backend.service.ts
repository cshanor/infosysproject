import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {HttpHeaders} from "@angular/common/http";
import {Observable, Subject} from "rxjs";

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'Accept': 'application/json'
  })
};

const LOCALHOST_USER = 'http://localhost:8080/AirlineReservationSystem_war/';
const LOCALHOST_FLIGHT = 'http://localhost:8080/flight';
const proxyurl = 'https://cors-anywhere.herokuapp.com/';


@Injectable({
  providedIn: 'root'
})
export class BackendService {

  myMethod$: Observable<any>;
  private myMethodSubject = new Subject();
  search = '';
  results: Object;

  constructor(private http: HttpClient) {
    this.myMethod$ = this.myMethodSubject.asObservable();
  }

  myMethod(data) {
    console.log(data);
    this.myMethodSubject.next(data);
  }

  authUser(user) {
    return this.http.post<any>(LOCALHOST_USER + 'user/auth', user, {observe: 'response'});
  }

  addUser(user) {
    console.log(user + 'in the addUser');
    return this.http.post(LOCALHOST_USER + 'user/add', user, {observe: 'response'});
  }

  getFlight(flightNumber) {
    return this.http.post(LOCALHOST_FLIGHT + 'flight/get', flightNumber, {observe: 'response'})
  }

}
