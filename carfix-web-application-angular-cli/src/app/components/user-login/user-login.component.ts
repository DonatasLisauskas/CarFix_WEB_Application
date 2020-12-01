import {Component, OnDestroy, OnInit} from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {AuthService} from '../../services/authorization_authentification/auth.service';
import {TokenService} from '../../services/token/token.service';
import {UserLogin} from '../../entities/user_login/user-login';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit {
  userLogin: UserLogin = new UserLogin();
  loginForm: FormGroup;
  errorMessage = '';
  loggedIn: boolean;
  submitted: boolean;
  userName: string;

  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private authService: AuthService,
              private tokenService: TokenService) {
  }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      username: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(20)]],
      password: ['', [Validators.required, Validators.minLength(6)]],
    });
  }

  get f(): { [p: string]: AbstractControl } {
    return this.loginForm.controls;
  }

  onSubmit(): void {
    this.errorMessage = '';
    this.loggedIn = false;
    this.userLogin.username = this.loginForm.get('username').value;
    this.userLogin.password = this.loginForm.get('password').value;
    this.authService.login(this.userLogin).subscribe(data => {
        this.tokenService.saveToken(data.token);
        this.tokenService.saveUser(data);
        this.loggedIn = true;
        this.userName = this.tokenService.getUser().username;
        this.navigateToPage();
      },
      error => {
        this.errorMessage = error.error.message;
        this.loggedIn = false;
      });
    this.userLogin = new UserLogin();
  }


navigateToPage(): void {
    setTimeout(() => {
      this.router.navigate(['profile'])
        .then(() => {
          window.location.reload();
        });
    }, 3000);
  }

  navigateToRegisterPage(): void {
    this.router.navigate(['registration']);
  }

}
