import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from '../Service/api.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private api: ApiService,private route:Router) { }

  ngOnInit(): void {

  }
  isLoggedIn() {
    return this.api.login;
  }

  logout(){
    this.api.logout()
  }

}
