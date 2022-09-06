import { HttpClient } from "@angular/common/http";
import { Component, OnInit } from "@angular/core";
import { Validators, FormGroup, FormBuilder, FormControl } from "@angular/forms";
import { Router } from "@angular/router";
import { ApiService } from "../Service/api.service";

@Component({
  selector: 'app-addproperty',
  templateUrl: './addproperty.component.html',
  styleUrls: ['./addproperty.component.css']
})
export class AddpropertyComponent implements OnInit {
  public propertyForm!: FormGroup;
  selectedImg!: File;
  selectedImg1!: File;
  selectedImg2!: File;
  selectedImg3!: File;


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
      features: new FormControl(null),
      description: new FormControl(null),
      phone: new FormControl(null),
      price: new FormControl(null),
      location: new FormControl(null),
      owner_id: new FormControl(this.api.getuserid())

    });
  }
  public onFileChanged1(event:any) {

    this.selectedImg1 = event.target.files[0];
    //console.log(this.selectedImg);
    console.log(event.target.files[0]);
  }
  public onFileChanged2(event:any) {

    this.selectedImg2 = event.target.files[0];
    //console.log(this.selectedImg);
    console.log(event.target.files[0]);
  }
  public onFileChanged3(event:any) {

    this.selectedImg3 = event.target.files[0];
    //console.log(this.selectedImg);
    console.log(event.target.files[0]);
  }
  public onFileChanged(event: any) {

    this.selectedImg = event.target.files[0];
    //console.log(this.selectedImg);
    console.log(event.target.files[0]);
  }
  Add() {

    // this.propertyForm.value.image = this.selectedImg

    const requestBody: FormData = new FormData();
    requestBody.append("name", this.propertyForm?.value.name)
    requestBody.append("type", this.propertyForm?.value.type)
    requestBody.append("features", this.propertyForm?.value.features)
    requestBody.append("description", this.propertyForm?.value.description)
    requestBody.append("phone", this.propertyForm?.value.phone)
    // requestBody.append("name",this.propertyForm?.value.name)
    requestBody.append("location", this.propertyForm?.value.location)
    console.log(requestBody);
    if (this.selectedImg != null)
      requestBody.append("image", this.selectedImg);
    console.log(requestBody)
    if(this.selectedImg1 != null)
    requestBody.append("image1", this.selectedImg1);
    if(this.selectedImg2 != null)
    requestBody.append("image2", this.selectedImg2);
    if(this.selectedImg3 != null)
    requestBody.append("image3", this.selectedImg3);
    console.log(this.propertyForm.value.owner_id);

    this.http.post<any>("http://localhost:8080/owners/addProperty/" + this.propertyForm.value.owner_id + "," + this.propertyForm.value.price, requestBody).subscribe(
      (res) => {
        console.log(res);
        alert("Property Added Succesfully");
        this.propertyForm?.reset();
        this.router.navigate(["ownerdashboard"]);
      },
      (err) => {
        alert("Property already added")
      }
    );

  }

}
