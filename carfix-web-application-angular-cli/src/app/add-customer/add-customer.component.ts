import {Component, OnInit} from '@angular/core';
import {Customer} from '../entities/CustomerEntity/customer';

import {AbstractControl, FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {CustomerService} from '../servicies/customerService/customer.service';
import {ActivatedRoute, Router} from '@angular/router';


@Component({
  selector: 'app-add-customer',
  templateUrl: './add-customer.component.html',
  styleUrls: ['./add-customer.component.css']
})
export class AddCustomerComponent implements OnInit {
  customer: Customer = new Customer();
  registerForm: FormGroup;
  submitted = false;

  constructor(private formBuilder: FormBuilder, private customerService: CustomerService, private router: Router) {
  }

  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      phoneNumber: ['', [Validators.required, Validators.pattern('^(\\+\\d{1,3}-)?\\d{1,14}$')]],
      /*password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', Validators.required]*/
    }, {});
  }

  // convenience getter for easy access to form fields
  get f(): { [p: string]: AbstractControl } {
    return this.registerForm.controls;
  }

  onSubmit(): void {
    this.submitted = true;
    if (this.registerForm.invalid) {
      return;
    } else {
      this.bindValues();
      this.create();
      setTimeout(() => {
        this.router.navigate(['/customers']);
      }, 5000);
    }
  }

  bindValues(): void {
    this.customer.firstName = this.registerForm.get('firstName').value;
    this.customer.lastName = this.registerForm.get('lastName').value;
    this.customer.email = this.registerForm.get('email').value;
    this.customer.phoneNumber = this.registerForm.get('phoneNumber').value;
  }

  create(): void {
    this.customerService.addNewCustomer(this.customer)
      .subscribe(data => console.log(data), error => console.log(error));
    this.customer = new Customer();
  }



}


