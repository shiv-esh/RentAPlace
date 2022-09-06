import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ApiService } from '../Service/api.service';

@Component({
  selector: 'app-emails',
  templateUrl: './emails.component.html',
  styleUrls: ['./emails.component.css']
})
export class EmailsComponent implements OnInit {
emails:any=[]
  constructor( private api: ApiService,private http: HttpClient) { }

  ngOnInit(): void {
    this.http.get<any>("http://localhost:8080/email/owner/".concat(`${this.api.getuserid()}`))
    .subscribe(res=>{
      this.emails=res;
      console.log(res);
    })
   
}

}
