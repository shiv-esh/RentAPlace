import { HttpClient } from "@angular/common/http";
import { Component, OnInit } from "@angular/core";
import { Validators, FormGroup, FormBuilder,FormControl } from "@angular/forms";
import { Router } from "@angular/router";
import { ApiService } from "../Service/api.service";

@Component({
  selector: 'app-addproperty',
  templateUrl: './addproperty.component.html',
  styleUrls: ['./addproperty.component.css']
})
export class AddpropertyComponent implements OnInit {
  public propertyForm!: FormGroup;

  constructor(
    private api: ApiService,
    private formBuilder: FormBuilder,
    private http: HttpClient,
    private router: Router
  ) { }

  ngOnInit(): void {

    this.propertyForm = new FormGroup({
      name: new FormControl(null),
      type: new FormControl(null),
     // features: new FormControl(null),
      description: new FormControl(null),
      phone: new FormControl(null),
      price: new FormControl(null),
      location: new FormControl(null),
owner_id: new FormControl(this.api.getuserid())
    });
  }

Add(){
  console.log(this.api.getuserid());
  let requestBody = this.propertyForm?.value;
  this.http.post<any>("http://localhost:8080/owners/addProperty",requestBody).subscribe(
    (res)=>{
      console.log(res);
      alert("Property Added Succesfully");
      this.propertyForm?.reset();
      this.router.navigate(["ownerdashboard"]);
    },
    (err)=>{
      alert("Property already added")
    }
  );

}

}
