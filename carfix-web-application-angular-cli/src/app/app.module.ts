import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {CustomersComponent} from './components/customers/customers.component';
import {HttpClientModule} from '@angular/common/http';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import {RoutingModule} from './routing/routing.module';
import {RouterModule} from '@angular/router';
import { AddCustomerComponent } from './add-customer/add-customer.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { CustomerEditorComponent } from './customer-editor/customer-editor.component';
import {authInterceptorProviders} from "./services/interceptor/auth.interceptor";

@NgModule({
  declarations: [
    AppComponent,
    CustomersComponent,
    NavBarComponent,
    AddCustomerComponent,
    CustomerEditorComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RoutingModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
