import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { DialogbodyComponent } from './dialogbody/dialogbody.component';
import { MatDialog } from '@angular/material/dialog';
import { SharedService } from './shared.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { DataService } from './data.service';



interface ApiResponse {
  content: any[];
  // Add other properties if needed
}

@Component({
  selector: 'app-donation-page',
  templateUrl: './donation-page.component.html',
  styleUrls: ['./donation-page.component.css']
})
export class DonationPageComponent implements OnInit {
  isLoggedIn: boolean = false;
  shouldShowButton: boolean = true;
  showEditProfile: boolean = true;
  userRole !: string;
  searchcampaign !: FormGroup
  campaignDta: any[] = [];

  constructor(private matDialog: MatDialog, private api: SharedService, private formbuilderr: FormBuilder, private router: Router, private dtaserv: DataService) {
    this.searchcampaign = this.formbuilderr.group({
      name: [''],
    });
    const token = localStorage.getItem('Token');
    this.isLoggedIn = !!token;
  }
  openDialog() {
    this.matDialog.open(DialogbodyComponent, {
      width: "900px",
      // height:"600px"
    })
  }

  ngOnInit() {
    this.getAllCampaigns();
    const storedUserRole = localStorage.getItem('Role');
    if (storedUserRole != null) {
      this.userRole = storedUserRole;
    }
    this.buttonRemoveBasedOnUser();
  }

  getAllCampaigns() {
    this.api.getCampaigns()
      .subscribe((res: ApiResponse) => {
        this.campaignDta = res.content;
        console.log(this.campaignDta)
      })

  }
  searchCampaigns() {
    const name = this.searchcampaign.value.name;

    this.api.getCampignsByName(name)
      .subscribe((res) => {
        this.campaignDta = [res]
        console.log(this.campaignDta);
      })
  }

  searchCampaignbycat() {
    const name = this.searchcampaign.value.name;
    this.api.getCampaignsByCategory(name)
      .subscribe((res) => {
        this.campaignDta = res
        console.log(this.campaignDta);
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

  // checkLoginRouteDonate() {
  //   if (this.isLoggedIn) {
  //     this.router.navigate(['/payment'])
  //   }
  //   else {
  //     this.router.navigate(['/login'])
  //   }
  // }

  // checkLoginRouteMyCampaign() {
  //     this.router.navigate(['/myCampiagn'])
  // }

  donateNow(num: any) {
    const cardName = num.nameOfTheProject;
    const cardDescription = num.projectDescription;
    const targetedAmount = num.targetedAmount;
    this.dtaserv.setDonationData({ name: cardName, description: cardDescription, Amount: targetedAmount });
    if (this.isLoggedIn) {
      this.router.navigate(['/payment']);
    } else {
      this.router.navigate(['/login']);
    }
  }

  buttonRemoveBasedOnUser() {
    if (this.userRole !== 'FundRaiser') {
      this.shouldShowButton = false;
    }
    else {
      this.shouldShowButton = true;
    }

  }
}

