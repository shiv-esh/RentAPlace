import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { faCalendar } from '@fortawesome/free-solid-svg-icons';
import { NgbDate } from '@ng-bootstrap/ng-bootstrap';
import { Property } from '../model/property.model';
import { ApiService } from '../Service/api.service';

@Component({
  selector: 'app-property',
  templateUrl: './property.component.html',
  styleUrls: ['./property.component.css']
})
export class PropertyComponent implements OnInit {
  pid!: number
  property: any = []
  images: any = []
  messageForm!: FormGroup
  faCalender = faCalendar
  checkin!: NgbDate
  checkout!: NgbDate
  checkInDate!: Date
  checkOutDate!: Date
  bookingdto = {
    pid: 0,
    user_id: 0,
    checkin: new Date(),
    checkout: new Date()


  }
  messages: any = []

  constructor(private formBuilder: FormBuilder, private api: ApiService, private http: HttpClient) {
    this.messageForm = this.formBuilder.group({
      pid: [''],
      message: [''],
      uid: [''],
      username: [''],
      oid: [''],
      ownername: [''],
      sid:['']


    });

   }

  ngOnInit(): void {
    this.api.propertyView$
      .subscribe(res => {
        if (res.returnedImage != null) {
          //console.log(res.returnedImage);
          this.images.push(this.convertImage(
            res.returnedImage
          ));
        }
        if (res.returnedImage1 != null) {
          this.images.push(this.convertImage(
            res.returnedImage1
          ));
        }
        if (res.returnedImage2 != null) {
          this.images.push(this.convertImage(
            res.returnedImage2
          ));
        }
        if (res.returnedImage3 != null) {
          this.images.push(this.convertImage(
            res.returnedImage3
          ));
        }
        this.property = res
        this.getMessages(res.pid, this.api.getuserid())
        console.log(this.property.returnedImage)


        

      })


  }
  onSubmit() {
    console.log(this.messageForm.value.message)
  }
  convertImage(img: any) {
    return "data:image/jpeg;base64," + img;
  }
  reserve(pid: number) {
    this.bookingdto.user_id = this.api.getuserid()
    this.checkInDate = new Date(
      this.checkin.year,
      this.checkin.month-1,
      this.checkin.day
    )
    this.checkOutDate = new Date(
      this.checkout.year,
      this.checkout.month-1,
      this.checkout.day
    )

    this.bookingdto.checkin = this.checkInDate
    this.bookingdto.checkout = this.checkOutDate
    this.bookingdto.pid = pid
    console.log(this.bookingdto);
    this.http.post<any>("http://localhost:8080/users/booking", this.bookingdto).subscribe((res) => {
      console.log("booking");
      console.log(res);

    },
    )
    this.sendEmail(pid, this.api.getuserid())
    alert("Booking successful!")

  }

  sendEmail(pid: number, user_id: number) {
    this.http.post<any>("http://localhost:8080/email/owner/".concat(`${pid}/${user_id}`), null).subscribe((res) => {
      console.log("booking");
      console.log(res);
    },
    )
  }

  send() {
    console.log("send")
    console.log(this.messageForm.value.message)
    this.http.post<any>("http://localhost:9090/chat/send/", {
      pid: this.property.pid,
      message: this.messageForm?.value.message,
      uid: this.api.getuserid(),
      username: this.api.getusername(),
      oid: this.property.owner_id,
      ownername: "owner1",
      sid:this.api.getuserid()
    }).subscribe(
      (res) => {
        console.log(res);
        alert("Message Sent");
        this.messageForm?.reset();
        this.messages.splice(0)
        this.getMessages(this.property.pid, this.api.getuserid())
        // this.router.navigate(["ownerdashboard"]);
      },
      (err) => {
        alert("Couldn't send msg")
      }
    );
  }

  getMessages(pid: number, uid: number) {
    const req = `${pid},${uid}`
    this.http.get<any>("http://localhost:9090/chat/get/".concat(req))
      .subscribe(res => {
        console.log("hello")
        console.log(res);
        this.messages = res;
      })

  }

}
