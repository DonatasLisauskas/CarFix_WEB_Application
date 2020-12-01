import {Component, OnInit} from '@angular/core';
import {TokenService} from '../../services/token/token.service';
import {UserRegistration} from '../../entities/user_registration/user-registration';
import {Router} from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  user: UserRegistration = new UserRegistration();
  roles: string[];
  loggedIn: boolean;

  constructor(private tokenService: TokenService, private router: Router) {
  }

  ngOnInit(): void {
    if (this.tokenService.getUser()) {
      this.loggedIn = true;
      this.user.firstname = this.tokenService.getUser().firstname;
      this.user.lastname = this.tokenService.getUser().lastname;
      this.user.email = this.tokenService.getUser().email;
      this.user.phoneNumber = this.tokenService.getUser().phoneNumber;
      this.user.username = this.tokenService.getUser().username;
      this.roles = this.tokenService.getUser().role;
    } else {
      this.navigateToLoginPage();
    }
  }

  navigateToLoginPage(): void {
    this.router.navigate(['signIn']);
  }

}
