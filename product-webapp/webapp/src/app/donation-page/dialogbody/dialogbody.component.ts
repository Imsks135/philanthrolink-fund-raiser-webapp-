import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { DashboardModel } from '../dashboardModel';
import { SharedService } from '../shared.service';

@Component({
  selector: 'app-dialogbody',
  templateUrl: './dialogbody.component.html',
  styleUrls: ['./dialogbody.component.css']
})

export class DialogbodyComponent implements OnInit, AfterViewInit{
  campigmmodelObj : DashboardModel = new DashboardModel();
  formValue !: FormGroup;

  constructor(private formbuilder: FormBuilder, private api:SharedService,){}

  ngOnInit(){
    this.formValue =this.formbuilder.group({
      projectId : [],
      name : [''],
      contactNumber: [],
      email : [''],
      nameOfTheProject: [''],
      imageUrl:[''],
      projectDescription:[''],
      targetedAmount: [],
      projectStatus: [0],
      category: ['']

    })
  }
  ngAfterViewInit() {
  }

  postCampaigndetails(){
    this.campigmmodelObj.name = this.formValue.value.name;
    this.campigmmodelObj.contactNumber = this.formValue.value.contactNumber;
    this.campigmmodelObj.email = this.formValue.value.email;
    this.campigmmodelObj.nameOfTheProject = this.formValue.value.nameOfTheProject;
    this.campigmmodelObj.imageUrl = this.formValue.value.imageUrl;
    this.campigmmodelObj.projectDescription = this.formValue.value.projectDescription;
    this.campigmmodelObj.targetedAmount = this.formValue.value.targetedAmount;
    this.campigmmodelObj.projectStatus = this.formValue.value.projectStatus;
    this.campigmmodelObj.category = this.formValue.value.category;
    this.campigmmodelObj.projectId = this.formValue.value.projectId;

    console.log(this.campigmmodelObj);
    this.api.postCampaign(this.campigmmodelObj)
    .subscribe(res => {
      console.log(res);
      alert("Campaign added successfully");
      let ref = document.getElementById("cancel");
      ref?.click();
      this.formValue.reset();
      location.reload()
    },
    err => {
      alert("Something went wrong");
    });
  
  }

}
