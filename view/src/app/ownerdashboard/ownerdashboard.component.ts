import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ApiService } from '../Service/api.service';
import { Validators, FormGroup, FormBuilder,FormControl } from "@angular/forms";
import { map } from 'rxjs';
import { Booking } from '../model/booking.model';
import { Router } from '@angular/router';
@Component({
  selector: 'app-ownerdashboard',
  templateUrl: './ownerdashboard.component.html',
  styleUrls: ['./ownerdashboard.component.css']
})
export class OwnerdashboardComponent implements OnInit {
public propertyList:any=[]
public priceUpdate!:FormGroup;
public bookings:any=[]
newPrice:string=""



  constructor(private api:ApiService,
    private http:HttpClient,
    private formBuilder:FormBuilder,
    private router:Router) 
    { 
    this.priceUpdate = this.formBuilder.group({
      price: ''
    });
  }

  ngOnInit(): void {
    const owner_id:string=`${this.api.getuserid()}`
    this.api.getOwnerProperties(owner_id)
    .subscribe(res=>{
      this.propertyList=res;
      for(let i=0;i<this.propertyList.length;i++){
        
        if(this.propertyList[i].booking.user_id!=0){
          let bookingObj=new Booking();
          let obj=this.propertyList[i].booking
         bookingObj.bid=obj.booking_id
         bookingObj.pid=this.propertyList[i].pid
         bookingObj.pname=this.propertyList[i].name
         if(obj.status==false)
         bookingObj.status="PENDING"
         else
         bookingObj.status="APPROVED"
         bookingObj.checkin=obj.checkin
         bookingObj.checkout=obj.checkout
         bookingObj.user_id=obj.user_id
         this.bookings.push(bookingObj)
         
        }
      }
      console.log(this.bookings)
    })
    
  }
  // myBooking(){
    
  //   for(let i=0;i<this.bookings.length;i++){
  //     let res=this.bookings[i]
      
  //   }
    
  // }
  
deleteProperty(pid:number){
  const property_id:string=`${pid}`
   return this.http.delete<any>("http://localhost:8080/owners/delete/".concat(property_id)).subscribe(
    res=>{
      console.log(res)
      alert("Deleted succesfully")
      this.bookings.splice(0)
      this.ngOnInit()
    }
  )
  
  
}
updatePrice(pid:number){
  // const property_price:string=`${this.price}`
  // const property_id:string=`${pid}`
  // const header:string:
  this.newPrice=this.priceUpdate.get('price')?.value
  return this.http.put<any>("http://localhost:8080/owners/updatePrice/".concat(`${pid}/`,this.newPrice),null).subscribe(
    res=>{
      console.log(this.newPrice)
      console.log(res)
      alert("Price Updated succesfully")
      this.bookings.splice(0)
      this.ngOnInit()
    }
  )
}

approveBooking(bid:number){
  return this.http.post<any>("http://localhost:8080/owners/approve/".concat(`${bid}/`),null).subscribe(
    res=>{
      // console.log(this.newPrice)
      console.log(res)
      alert("Booking approved")
      this.bookings.splice(0)
      this.ngOnInit()
    }
  )
}

disapproveBooking(bid:number){
  return this.http.post<any>("http://localhost:8080/owners/disapprove/".concat(`${bid}/`),null).subscribe(
    res=>{
      // console.log(this.newPrice)
      console.log(res)
      alert("Booking Disapproved")
      this.bookings.splice(0)
      this.ngOnInit()
    }
  )
}

viewMessages(pid:number){
  this.api.getPropertyMessage(pid)
  this.router.navigate(['/messages'])
}
}
