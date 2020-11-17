import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Customer} from '../../entities/CustomerEntity/customer';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  url = 'http://localhost:8080/carfix/customers';

  constructor(private http: HttpClient) {}

  getAllCustomers(): Observable<Customer[]> {
    return this.http.get<Customer[]>(`${this.url}`);
  }

}
