import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {UserLogin} from '../../entities/user_login/user-login';
import {Observable} from 'rxjs';
import {UserRegistration} from '../../entities/user_registration/user-registration';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  apiAuthorization = 'http://localhost:8080/api/authorization/';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) { }

  login(userLogin: UserLogin): Observable<any> {
    return this.http.post(`${this.apiAuthorization}` + `signin`, userLogin, this.httpOptions);
  }

  registration(userRegistration: UserRegistration): Observable<any> {
    return this.http.post(`${this.apiAuthorization}` + `signup`, userRegistration, this.httpOptions);
  }
}
