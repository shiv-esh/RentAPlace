import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { UserdashboardComponent } from './userdashboard/userdashboard.component';
import { OwnersignupComponent } from './ownersignup/ownersignup.component';
import { SignupComponent } from './signup/signup.component';
import { OwnerdashboardComponent } from './ownerdashboard/ownerdashboard.component';
import { AddpropertyComponent } from './addproperty/addproperty.component';
import { HeaderComponent } from './header/header.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,

    OwnerdashboardComponent,
    AddpropertyComponent,
    
    UserdashboardComponent,
  
    OwnersignupComponent,
       HeaderComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgbModule
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
