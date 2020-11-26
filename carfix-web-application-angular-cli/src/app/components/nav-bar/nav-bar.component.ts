import { Component, OnInit } from '@angular/core';
import {TokenService} from '../../services/token/token.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {
  private roles: string[];
  isLoggedIn = false;
  showAdminBoard = false;
  showModeratorBoard = false;
  username: string;

  constructor(private tokenService: TokenService) { }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenService.getUser();
      this.roles = user.roles;
      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      this.showModeratorBoard = this.roles.includes('ROLE_MODERATOR');
      this.username = user.username;
    }
  }

  logout(): void {
    this.tokenService.signOut();
    window.location.reload();
  }
}

 /* openNav(): void {
    document.getElementById('mySidenav').style.width = '15%';
  }

  closeNav(): void {
    document.getElementById('mySidenav').style.width = '0';
  }*/
