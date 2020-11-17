import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {CustomersComponent} from './components/customers/customers.component';
import {HttpClientModule} from '@angular/common/http';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import {RoutingModule} from './routing/routing.module';
import {RouterModule} from '@angular/router';

@NgModule({
  declarations: [
    AppComponent,
    CustomersComponent,
    NavBarComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RoutingModule,
    RouterModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
