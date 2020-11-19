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
  postError = false;
  postErrorMsg: string;
  customer: Customer = new Customer();
  submitted = false;

  constructor(private activatedRoute: ActivatedRoute,
              private customerService: CustomerService,
              private router: Router) {
  }

  ngOnInit(): void {
  }
  newCustomer(): void {
    this.submitted = false;
    this.customer = new Customer();
  }

  save(): void {
    this.customerService.addNewCustomer(this.customer)
      .subscribe(
        result => console.log(`Success: `, result),
        error => this.onHttpError(error),
        () => console.log(`Completed!`));
    this.customer = new Customer();
  }

  onSubmit(): void {
    this.submitted = true;
    this.save();
    console.log(this.postErrorMsg);
  }

  private onHttpError(error: Error): void {
    console.log(`Error: `, error);
    this.postError = true;
    console.log(this.postError);
    this.postErrorMsg = error.message;
  }
/*  customer: Customer = new Customer();
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
      /!*password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', Validators.required]*!/
    }, {});
  }

  // convenience getter for easy access to form fields
  get f(): { [p: string]: AbstractControl } {
    return this.registerForm.controls;
  }

  onSubmit(): void {
    this.submitted = true;
    if (this.registerForm.invalid) {
      // save data
    }else {
      this.submitted = false;
      this.validateAllFields(this.registerForm);
    }
  }

  validateAllFields(formGroup: FormGroup): void {
    Object.keys(formGroup.controls).forEach(field => {
      const control = formGroup.get(field);
      if (control instanceof FormControl) {
        control.markAsTouched({ onlySelf: true });
      } else if (control instanceof FormGroup) {
        this.validateAllFields(control);
      }
    });
  }

 /!* onSubmit(): void {
    this.submitted = true;
    if (this.registerForm.invalid) {
      return;
    }else {
      this.router.navigate(['/customers/new']);
      this.submitted = false;
      localStorage.setItem('data', JSON.stringify(this.registerForm.value));
    }
  }*!/*/

}


