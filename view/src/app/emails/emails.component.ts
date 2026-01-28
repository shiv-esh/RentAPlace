import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ApiService } from '../Service/api.service';

interface EmailGroup {
  user_id: number;
  useremail: string;
  emails: any[];
  totalMessages: number;
  latestMessage: string;
  latestDate?: Date;
  properties: Set<number>;
}

@Component({
  selector: 'app-emails',
  templateUrl: './emails.component.html',
  styleUrls: ['./emails.component.css']
})
export class EmailsComponent implements OnInit {
  emails: any[] = [];
  groupedEmails: EmailGroup[] = [];
  selectedGroup: EmailGroup | null = null;

  constructor(private api: ApiService, private http: HttpClient) { }

  ngOnInit(): void {
    this.http.get<any>("http://localhost:8080/email/owner/".concat(`${this.api.getuserid()}`))
      .subscribe(res => {
        this.emails = res;
        console.log(res);
        this.groupEmailsByUser();
      })
  }

  groupEmailsByUser() {
    const emailMap = new Map<number, EmailGroup>();

    this.emails.forEach(email => {
      if (!emailMap.has(email.user_id)) {
        emailMap.set(email.user_id, {
          user_id: email.user_id,
          useremail: email.useremail,
          emails: [],
          totalMessages: 0,
          latestMessage: '',
          properties: new Set<number>()
        });
      }

      const group = emailMap.get(email.user_id)!;
      group.emails.push(email);
      group.totalMessages++;
      group.latestMessage = email.emailBody;
      group.properties.add(email.pid);
    });

    this.groupedEmails = Array.from(emailMap.values())
      .sort((a, b) => b.totalMessages - a.totalMessages);
  }

  selectGroup(group: EmailGroup) {
    this.selectedGroup = this.selectedGroup === group ? null : group;
  }

  getPropertyList(properties: Set<number>): string {
    return Array.from(properties).map(p => `#${p}`).join(', ');
  }
}
