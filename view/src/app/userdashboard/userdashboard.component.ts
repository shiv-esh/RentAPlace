import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { from, NEVER } from 'rxjs';
import { Booking } from '../model/booking.model';
import { Property } from '../model/property.model';
import { ApiService } from '../Service/api.service';

@Component({
  selector: 'app-userdashboard',
  templateUrl: './userdashboard.component.html',
  styleUrls: ['./userdashboard.component.css']
})
export class UserdashboardComponent implements OnInit {

  property!: Property;
  propertys : any =[] ;
  a : number =0
  bookingdto={
    pid :0,
    user_id : 0,
    checkin : "2022-09-02",
    checkout : "2022-09-03"


  }




  constructor(
    private api: ApiService,
    private http: HttpClient,
    private router: Router
  ) {}

  ngOnInit(): void {

this.getProperty()


  }

  getProperty(){
    console.log("getProperty called")
    this.http.get<any>("http://localhost:8080/users/statusProperty").subscribe((res)=>{

      //this.propertys.push(res[0])

      console.log(res.length,"length")
for(let i = 0; i < res.length; i++){

  this.property = res[i]
  console.log(res[i])
  this.propertys.push(this.property)
  this.a=this.propertys[0].pid
}



    })
    //this.get()
  }
  reserve(pid:number){
    this.bookingdto.user_id=this.api.getuserid()

    this.bookingdto.pid=pid
    console.log(this.bookingdto);
    this.http.post<any>("http://localhost:8080/users/booking",this.bookingdto).subscribe((res)=>{
      console.log("booking");
      console.log(res);
    },
   )

  }



}
