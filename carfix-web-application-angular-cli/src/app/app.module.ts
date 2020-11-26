import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import {HttpClientModule} from '@angular/common/http';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import {RoutingModule} from './routing/routing.module';
import {RouterModule} from '@angular/router';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { CustomerEditorComponent } from './customer-editor/customer-editor.component';
import { UserRegistrationComponent } from './components/user_registration/user-registration.component';
import { HomeComponent } from './components/home/home/home.component';
import {authInterceptorProviders} from './services/interceptor/auth.interceptor';

@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    CustomerEditorComponent,
    UserRegistrationComponent,
    HomeComponent,
  ],
  imports: [
    BrowserModule,
    RoutingModule,
    RouterModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
