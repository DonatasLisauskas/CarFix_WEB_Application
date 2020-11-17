import {Component, OnInit} from '@angular/core';
import {Customer} from '../../entities/CustomerEntity/customer';
import {CustomerService} from '../../servicies/customerService/customer.service';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent implements OnInit {

  customers: Observable<Customer[]>;

  constructor(private customerService: CustomerService) {
  }

  ngOnInit(): void {
    this.reloadData();
  }

  reloadData(): void {
    this.customers = this.customerService.getAllCustomers();
  }


}
