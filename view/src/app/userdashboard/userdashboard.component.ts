import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { faCalendar } from '@fortawesome/free-solid-svg-icons'
import { NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';
import { Booking } from '../model/booking.model';
import { Property } from '../model/property.model';
import { ApiService } from '../Service/api.service';
@Component({
  selector: 'app-userdashboard',
  templateUrl: './userdashboard.component.html',
  styleUrls: ['./userdashboard.component.css']
})
export class UserdashboardComponent implements OnInit {
  faCalender = faCalendar
  checkin!: NgbDateStruct
  checkout!: NgbDateStruct
  checkInDate!: Date
  searchText: string = ""
  checkOutDate!: Date

  property!: Property;
  propertys: any = [];
  a: number = 0
  bookingdto = {
    pid: 0,
    user_id: 0,
    checkin: new Date(),
    checkout: new Date()


  }





  constructor(
    private api: ApiService,
    private http: HttpClient,
    private router: Router
  ) { }

  ngOnInit(): void {

    this.getProperty()


  }
  // Convert image URL to full URL with backend server
  convertImageUrl(url: string | null): string | null {
    if (!url) return null;
    // If it's already a full URL (starts with http), return as is
    if (url.startsWith('http')) return url;
    // Otherwise, prepend the backend URL
    return 'http://localhost:8080' + url;
  }

  getProperty() {
    console.log("getProperty called")
    this.http.get<any>("http://localhost:8080/users/statusProperty")
      .subscribe((res) => {

        console.log(res.length, "length")
        for (let i = 0; i < res.length; i++) {
          // Convert image URLs to full URLs
          if (res[i].imageUrl != null) {
            res[i].returnedImage = this.convertImageUrl(res[i].imageUrl);
          }
          if (res[i].imageUrl1 != null) {
            res[i].returnedImage1 = this.convertImageUrl(res[i].imageUrl1);
          }
          if (res[i].imageUrl2 != null) {
            res[i].returnedImage2 = this.convertImageUrl(res[i].imageUrl2);
          }
          if (res[i].imageUrl3 != null) {
            res[i].returnedImage3 = this.convertImageUrl(res[i].imageUrl3);
          }
          this.property = res[i]
          console.log(res[i].returnedImage)
          this.propertys.push(res[i])
          this.a = this.propertys[0].pid
        }

      })
    //this.get()
  }


  search() {


    let checkInDate = this.checkin.year + "-" + this.checkin.month + "-" + this.checkin.day
    let checkOutDate = this.checkout.year + "-" + this.checkout.month + "-" + this.checkout.day
    // new Date(
    //   this.checkin.year,
    //   this.checkin.month,
    //   this.checkin.day
    // )
    this.checkOutDate = new Date(
      this.checkout.year,
      this.checkout.month,
      this.checkout.day
    )



    this.http.get<any>("http://localhost:8080/users/search/" + checkInDate + "/" + checkOutDate)
      .subscribe((res) => {
        console.log("booking");
        this.propertys.splice(0)

        console.log(res);
        for (let i = 0; i < res.length; i++) {
          // Convert image URLs to full URLs
          if (res[i].imageUrl != null) {
            res[i].returnedImage = this.convertImageUrl(res[i].imageUrl);
          }
          if (res[i].imageUrl1 != null) {
            res[i].returnedImage1 = this.convertImageUrl(res[i].imageUrl1);
          }
          if (res[i].imageUrl2 != null) {
            res[i].returnedImage2 = this.convertImageUrl(res[i].imageUrl2);
          }
          if (res[i].imageUrl3 != null) {
            res[i].returnedImage3 = this.convertImageUrl(res[i].imageUrl3);
          }
          this.property = res[i]
          console.log(res[i].returnedImage)
          this.propertys.push(res[i])
          this.a = this.propertys[0].pid
        }
      },
      )

  }

  viewProperty(pid: number) {
    this.api.viewPropertyDetails(pid)
    this.router.navigate(['/property'])
  }


}
