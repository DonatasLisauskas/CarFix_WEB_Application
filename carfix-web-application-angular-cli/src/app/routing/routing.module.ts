import {NgModule, OnInit} from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {UserRegistrationComponent} from '../components/user_registration/user-registration.component';
import {HomeComponent} from '../components/home/home.component';
import {ProfileComponent} from '../components/profile/profile.component';
import {UserLoginComponent} from '../components/user-login/user-login.component';
import {TokenService} from '../services/token/token.service';

const routes: Routes = [
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  {path: 'home', component: HomeComponent},
  {path: 'signIn', component: UserLoginComponent},
  {path: 'registration', component: UserRegistrationComponent},
  {path: 'profile', component: ProfileComponent},

];

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forRoot(routes),
    CommonModule
  ]
})
export class RoutingModule {
}
