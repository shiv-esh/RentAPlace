
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Subject } from 'rxjs';

const TOKEN = 'u_token';
const USERNAME = 'u_username';
const USERID = 'u_userid';
const ROLE = 'u_role';



@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(
    private http: HttpClient
  ) { }

  searchString: string = "";
  username: string = window.localStorage.getItem(USERNAME) || "";
  login: boolean = !!window.localStorage.getItem(TOKEN);
  userid: number = Number(window.localStorage.getItem(USERID)) || 0;
  role: string = window.localStorage.getItem(ROLE) || "";
  private _propertySource = new Subject<any>();
  propertyView$ = this._propertySource.asObservable();
  private _propertyMessage = new Subject<any>();
  messageView$ = this._propertyMessage.asObservable();
  token: string = "";
  setUserName(name: string) {
    window.localStorage.removeItem(USERNAME)
    window.localStorage.setItem(USERNAME, name);
  }
  setUserId(userid: number) {
    window.localStorage.setItem(USERID, String(userid));
    this.userid = userid;
  }

  setRole(role: string) {
    window.localStorage.setItem(ROLE, role);
    this.role = role;
  }

  getRole() {
    return this.role;
  }

  setToken(importtoken: string) {
    window.localStorage.setItem(TOKEN, importtoken);
    this.login = true;
  }
  getToken() {
    return window.localStorage.getItem(TOKEN);
  }
  setusername(name: string) {
    this.username = name;
    window.localStorage.setItem(USERNAME, name);
  }
  setuserid(id: number) {
    this.userid = id;
    window.localStorage.setItem(USERID, String(id));
  }
  getuserid() {
    if (this.userid === 0) {
      this.userid = Number(window.localStorage.getItem(USERID)) || 0;
    }
    return this.userid;
  }
  getusername() {
    if (this.username === "") {
      this.username = window.localStorage.getItem(USERNAME) || "";
    }
    return this.username
  }

  getProperty() {
    console.log("getProperty called")
    return this.http.get<any>("http://localhost:8080/users/statusProperty").pipe(map((res: any) => {
      return res;
    })
    )
  }
  getOwnerProperties(owner_id: string) {
    console.log("getProperty called")
    return this.http.get<any>("http://localhost:8080/owners/myProperties/".concat(owner_id)).pipe(map((res: any) => {
      return res;
    })
    )
  }

  getPropertyDetails(pid: number) {
    console.log("getProperty called")

    return this.http.get<any>("http://localhost:8080/property/".concat(`${pid}`)).pipe(map((res: any) => {
      return res;
    })
    )
  }

  viewPropertyDetails(pid: number) {
    // this._propertySource.next(pid)
    this.getPropertyDetails(pid)
      .subscribe(res => {
        this._propertySource.next(res)
      })
  }
  getPropertyMessage(pid: number) {
    return this.http.get<any>("http://localhost:9090/chat/getowner/".concat(`${pid}`))
      .subscribe(res => {
        this._propertyMessage.next(res)
      })


  }


  logout() {
    window.localStorage.removeItem(USERNAME);
    window.localStorage.removeItem(TOKEN);
    window.localStorage.removeItem(USERID);
    window.localStorage.removeItem(ROLE);
    this.login = false;
    this.username = "";
    this.userid = 0;
    this.role = "";
  }



}