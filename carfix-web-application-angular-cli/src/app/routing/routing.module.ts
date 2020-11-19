import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {CustomersComponent} from '../components/customers/customers.component';
import {AddCustomerComponent} from '../add-customer/add-customer.component';

const routes: Routes = [
  {path: '', redirectTo: 'customers', pathMatch: 'full'},
  {path: 'customers', component: CustomersComponent},
  {path: 'customers/new', component: AddCustomerComponent},

];

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forRoot(routes),
    CommonModule
  ]
})
export class RoutingModule { }
