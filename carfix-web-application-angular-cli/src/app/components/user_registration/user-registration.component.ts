import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {UserRegistration} from '../../entities/user_registration/user-registration';
import {Router} from '@angular/router';
import {AuthService} from '../../services/authorization_authentification/auth.service';

@Component({
  selector: 'app-user-registration',
  templateUrl: './user-registration.component.html',
  styleUrls: ['./user-registration.component.css']
})
export class UserRegistrationComponent implements OnInit {

  user: UserRegistration = new UserRegistration();
  registerForm: FormGroup;
  submitted = false;
  errorMessage = '';
  isSignUpFailed = false;

  constructor(private formBuilder: FormBuilder, private router: Router, private authService: AuthService) { }

  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email, Validators.maxLength(50)]],
      phoneNumber: ['', [Validators.required, Validators.pattern('^(\\+\\d{1,3}-)?\\d{1,14}$'), Validators.maxLength(20)]],
      username: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(20)]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', Validators.required]
    }, {
      validator: this.mustMatch('password', 'confirmPassword')
    });
  }

  mustMatch(controlName: string, matchingControlName: string): (formGroup: any) => void {
    return (formGroup: FormGroup) => {
      const control = formGroup.controls[controlName];
      const matchingControl = formGroup.controls[matchingControlName];

      if (matchingControl.errors && !matchingControl.errors.mustMatch) {
        return;
      }
      if (control.value !== matchingControl.value) {
        matchingControl.setErrors({mustMatch: true});
      } else {
        matchingControl.setErrors(null);
      }
    };
  }

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
      this.isSignUpFailed = false;
      this.errorMessage = '';
    }
  }

  bindValues(): void {
    this.user.firstname = this.registerForm.get('firstName').value;
    this.user.lastname = this.registerForm.get('lastName').value;
    this.user.email = this.registerForm.get('email').value;
    this.user.phoneNumber = this.registerForm.get('phoneNumber').value;
    this.user.username = this.registerForm.get('username').value;
    this.user.password = this.registerForm.get('password').value;
  }

  create(): void {
    this.authService.registration(this.user)
      .subscribe(data => console.log(data), error => {this.errorMessage = error.error.message; this.isSignUpFailed = true; });
    this.user = new UserRegistration();
  }
}
