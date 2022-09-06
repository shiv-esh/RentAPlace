
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Subject } from 'rxjs';

const TOKEN = 'u_token';
const USERNAME = 'u_username';
const USERID = '0';



@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(
    private http: HttpClient
  ) { }

  searchString: string = "";
  username: string = "";
  login: boolean = false
  userid: number = 0;
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

    window.localStorage.removeItem(USERID)
    window.localStorage.setItem(USERID, String(userid));
  }

  setToken(importtoken: string) {
    window.localStorage.removeItem(TOKEN)
    window.localStorage.setItem(TOKEN, importtoken);
  }
  getToken() {
    return window.localStorage.getItem(TOKEN);
  }
setusername(name:string){
  this.username=name
}
  setuserid(id: number) {
    this.userid = id;
  }
  getuserid() {
    return this.userid;
  }
  getusername(){
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
getPropertyMessage(pid:number){
  return this.http.get<any>("http://localhost:9090/chat/getowner/".concat(`${pid}`))
  .subscribe(res=>{
    this._propertyMessage.next(res)
  })
 

}


  logout() {
    window.localStorage.removeItem(USERNAME);
    window.localStorage.removeItem(TOKEN);
    this.login = false;
  }



}