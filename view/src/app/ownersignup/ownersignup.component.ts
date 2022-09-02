import { HttpClient } from "@angular/common/http";
import { Component, OnInit } from "@angular/core";
import { Validators, FormGroup, FormBuilder,FormControl } from "@angular/forms";
import { Router } from "@angular/router";

@Component({
  selector: 'app-ownersignup',
  templateUrl: './ownersignup.component.html',
  styleUrls: ['./ownersignup.component.css']
})
export class OwnersignupComponent implements OnInit {

  public signupForm!: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private http: HttpClient,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.signupForm = new FormGroup({

      username: new FormControl ,
      name:new FormControl ,
      email: new FormControl,
      password:new FormControl ,

      phone:new FormControl,
      role : new FormControl("owner")
    });

  }

  signUp() {
    if (this.signupForm.valid) {
      let signUpRequestBody = this.signupForm?.value;
      this.http
        .post<any>("http://localhost:8080/users/signUp", signUpRequestBody)
        .subscribe(
          (res) => {
            console.log(res);

            alert("Sign Up Is Done Succesfully");
            this.signupForm?.reset();
            this.router.navigate(["login"]);
          },
          (err) => {
            alert("username already exists change user name");
          }
        );
    }
    else{
      alert("Please fill required details ");
    }
  }


}
