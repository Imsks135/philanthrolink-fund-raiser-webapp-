import { Component, OnInit } from '@angular/core';
import { DialogbodyComponent } from '../dialogbody/dialogbody.component';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { SharedService } from '../shared.service';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { DashboardModel } from '../dashboardModel';



@Component({
  selector: 'app-my-campaign',
  templateUrl: './my-campaign.component.html',
  styleUrls: ['./my-campaign.component.css']
})
export class MyCampaignComponent implements OnInit {
  formModal: any;
  decoded: any
  isLoggedIn: boolean = false;
  campaignDta: any[] = [];
  formValue !: FormGroup;
  campigmmodelObj : DashboardModel = new DashboardModel();
  constructor(private matDialog: MatDialog, private router: Router, private api: SharedService, private formbuilder: FormBuilder){
    const token = localStorage.getItem('Token');
    this.isLoggedIn = !!token;
  }
  ngOnInit(): void {
    this.gedCampaignByemail();
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

  logoutOrRedirect() {
    if (this.isLoggedIn) {
      localStorage.clear();
      this.isLoggedIn = false;
      this.router.navigate(['']);
    } else {
      this.router.navigate(['/login']);
    }
  }

  gedCampaignByemail(){
    this.decoded= this.api.getToken();
    let email = this.decoded['userEmail']
    this.api.getDtabyEmail(email)
    .subscribe((res)=>{
      this.campaignDta = res
    })
    

  }


  openDialog(){
    this.matDialog.open(DialogbodyComponent,{
      width: "900px",
      // height:"600px"
    })
  }

  deleteYourCampaign(num: any){
    this.api.deleteCampaign(num.projectId)
    .subscribe(res=>{
      alert("Campaign Deleted")
      location.reload()
    })
  }

  onEdit(num: any){
    console.log('Received num:', num);
    this.campigmmodelObj.projectId = num.projectId;
    this.formValue.controls['projectId'].setValue(num.projectId);
    this.formValue.controls['name'].setValue(num.name);
    this.formValue.controls['contactNumber'].setValue(num.contactNumber);
    this.formValue.controls['email'].setValue(num.email);
    this.formValue.controls['nameOfTheProject'].setValue(num.nameOfTheProject);
    this.formValue.controls['imageUrl'].setValue(num.imageUrl);
    this.formValue.controls['projectDescription'].setValue(num.projectDescription);
    this.formValue.controls['targetedAmount'].setValue(num.targetedAmount);
    this.formValue.controls['projectStatus'].setValue(num.projectStatus);
    this.formValue.controls['category'].setValue(num.category);
  }

  updatedEmployee(){
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

    this.api.UpdateProject(this.campigmmodelObj, this.campigmmodelObj.projectId)
    .subscribe(res=>{
      alert("Updated Successfully");
      let ref = document.getElementById("cancel");
      ref?.click();
      this.formValue.reset();
      this.gedCampaignByemail();
    },
    err=>{
      alert("something went wrong")
    })
  }

}
