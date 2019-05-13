import { Injectable } from "@angular/core";
import {
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest
} from "@angular/common/http";
import { Observable } from "rxjs";

const LOCALHOST = 'http://localhost:8080/';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor() {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    console.log('Http request intercepted!');
    let token = window.localStorage.getItem('jwt');
    if (request.url.indexOf(LOCALHOST) >= 0 && token) {
        console.log('Attaching JWT to Authorization header...')
        request = request.clone({
          setHeaders: {
            'Authorization': token,
            'Content-Type': 'application/json',
            'Accept': 'application/json'
          }
        });
        console.log('JWT attached!');
    } else {
      console.log('No JWT present, attaching other headers');
      request = request.clone({
        setHeaders: {
          'Content-Type': 'application/json',
          'Accept': 'application/json'
        }
      });
    }

    console.log('Sending HTTP request to HttpHandler');
    return next.handle(request);
  }

}
