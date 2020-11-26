import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {UserRegistrationComponent} from '../components/user_registration/user-registration.component';
import {HomeComponent} from '../components/home/home/home.component';

const routes: Routes = [
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  {path: 'home', component: HomeComponent},
  {path: 'registration', component: UserRegistrationComponent},

];

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forRoot(routes),
    CommonModule
  ]
})
export class RoutingModule { }
