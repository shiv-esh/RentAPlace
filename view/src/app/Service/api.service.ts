
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs';

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

  searchString: string="";
  username: string="";
  
  userid:number=0;

  token: string="";
  setUserName(name: string) {
    window.localStorage.removeItem(USERNAME)
    window.localStorage.setItem(USERNAME,name);
  }
  setUserId(userid: number) {

  window.localStorage.removeItem(USERID)
  window.localStorage.setItem(USERID,String(userid));
  }
  
  setToken(importtoken: string) {
    window.localStorage.removeItem(TOKEN)
    window.localStorage.setItem(TOKEN,importtoken);
  }
  getToken() {
    return window.localStorage.getItem(TOKEN);
  }
  setuserid(id: number) {
    this.userid = id;
  }
  getuserid() {
    return this.userid;
  }
  getData() {
    return this.http.get<any>("http://localhost:9090/questions").pipe(    //change accordingly
      map((res: any) => {
        return res;
      })
    );
  }
  getProperty(){
    console.log("getProperty called")
    return this.http.get<any>("http://localhost:8080/users/statusProperty").pipe(map((res:any)=>{
      return res;
    })
    )
  }


  logout(){
    window.localStorage.removeItem(USERNAME);
    window.localStorage.removeItem(TOKEN);
  }

  getAll() {
    return this.http.get<any>("http://localhost:9090/admin/getAllUsers")        //change accordingly
  }

}