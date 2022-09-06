
import { HttpClient } from "@angular/common/http";
import { Component, OnInit } from "@angular/core";
import { Validators, FormBuilder, FormGroup, FormControl } from "@angular/forms";
import { Router } from "@angular/router";
import { ApiService } from "../Service/api.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  public loginForm!: FormGroup;
  name: string | undefined;
  role: string | undefined;


  constructor(
    private api: ApiService,
    private formBuilder: FormBuilder,
    private http: HttpClient,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loginForm = new FormGroup({
      username: new FormControl(null, Validators.required),
      password: new FormControl(null, Validators.required),
    });
  }
  login() {


    let logInRequestBody = this.loginForm?.value;
    this.http.post<any>("http://localhost:8080/signIn/user", logInRequestBody).subscribe(
      (res) => {



        this.api.setToken(res.token);

        this.api.setUserName(this.loginForm.value.username);
        this.api.setUserId(res.id)
        this.role = res.role;
        this.api.setuserid(res.id);
        this.api.setusername(this.loginForm.value.username)
        this.api.login=true
        if(this.role=="user")
        {this.router.navigate(["userdashboard"])
        console.log("user")}
        else{
          console.log("owner")
          this.router.navigate(["ownerdashboard"]);
        }

      }, (err) => {
        alert("Invalid Credentials");
      }

    );

  }


}
