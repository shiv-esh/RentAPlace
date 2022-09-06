import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormBuilder, FormsModule, ReactiveFormsModule } from '@angular/forms';
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
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { PropertyComponent } from './property/property.component';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { EmailsComponent } from './emails/emails.component';
import { MessagesComponent } from './messages/messages.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,

    OwnerdashboardComponent,
    AddpropertyComponent,
    
    UserdashboardComponent,
  
    OwnersignupComponent,
       HeaderComponent,
       PropertyComponent,
       EmailsComponent,
       MessagesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    
    ReactiveFormsModule,
    HttpClientModule,
    NgbModule,
    FontAwesomeModule,
    Ng2SearchPipeModule
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
