import { HttpClient } from '@angular/common/http';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ApiService } from '../Service/api.service';
import { interval, Subscription, switchMap } from 'rxjs';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit, OnDestroy {
  messages: any = []
  messageForm: any
  private pollingSubscription?: Subscription;

  constructor(private api: ApiService,
    private formBuilder: FormBuilder,
    private http: HttpClient) {
    this.messageForm = this.formBuilder.group({
      pid: [''],
      message: [''],
      uid: [''],
      username: [''],
      oid: [''],
      ownername: [''],
      sid: ['']


    });
  }

  ngOnInit(): void {
    // Initial load from observable
    this._loadInitialMessages();

    // Setup real-time polling (every 3 seconds)
    this.pollingSubscription = interval(3000).pipe(
      switchMap(() => this.http.get<any>("http://localhost:9090/chat/owner/".concat(`${this.api.getuserid()}`)))
    ).subscribe(res => {
      this.messages = res;
    });
  }

  private _loadInitialMessages() {
    this.api.messageView$.subscribe(res => {
      this.messages = res;
      console.log(this.messages)
    });
  }

  ngOnDestroy(): void {
    if (this.pollingSubscription) {
      this.pollingSubscription.unsubscribe();
    }
  }
  send(pid: number, uid: number, username: string) {
    console.log("send")
    console.log(this.messageForm.value.message)

    if (!this.messageForm.value.message || this.messageForm.value.message.trim() === '') {
      alert("Please enter a message");
      return;
    }

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

        // Clear the form
        this.messageForm?.reset();

        // Reload messages from server immediately
        this.refreshMessages();

        alert("Message sent successfully!");
      },
      (err) => {
        console.error(err);
        alert("Couldn't send message. Please try again.");
      }
    );
  }

  refreshMessages() {
    this.http.get<any>("http://localhost:9090/chat/owner/".concat(`${this.api.getuserid()}`))
      .subscribe((messages) => {
        this.messages = messages;
      });
  }

}
