import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ApiService } from '../Service/api.service';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit {
  messages:any=[]
  messageForm:any
  constructor(private api:ApiService,
    private formBuilder:FormBuilder,
    private http:HttpClient) { 
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
    this.api.messageView$.subscribe(res=>{
      this.messages=res;
      console.log(this.messages)
    })
  }
  send(pid:number,uid:number,username:string) {
    console.log("send")
    console.log(this.messageForm.value.message)
    this.http.post<any>("http://localhost:9090/chat/send/", {
      pid: pid,
      message: this.messageForm?.value.message,
      uid: uid,
      username: username,
      oid: this.api.getuserid(),
      ownername: this.api.getusername(),
      sid: this.api.getuserid()
    }).subscribe(
      (res) => {
        console.log(res);
        alert("Message Sent");
        this.messageForm?.reset();
        // this.messages.splice(0)
        this.ngOnInit()
        // this.router.navigate(["ownerdashboard"]);
      },
      (err) => {
        alert("Couldn't send msg")
      }
    );
  }

}
