import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { EditProfileService } from '../Services/edit-profile.service';
import { SharedService } from '../donation-page/shared.service';
import { DialogbodyComponent } from '../donation-page/dialogbody/dialogbody.component';
import { MatDialog } from '@angular/material/dialog';

export class UserData {
  email: string | undefined;
}

export class DonationData {
  name: string | undefined;
  description: string | undefined;
  Amount: string | undefined;
}

export class Data {
  userData: UserData | undefined;
  donationData: DonationData | undefined;
  amount: string | undefined;
  timeStamp!: string;
}

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {
  isLoggedIn: boolean = false;
  userRole !: string;
  shouldShowButton: boolean = true;
  decoded: any;
  constructor(private router: Router, private http: HttpClient, private edituserprofile: EditProfileService, private api: SharedService, private matDialog: MatDialog) {
    const token = localStorage.getItem('Token');
    this.isLoggedIn = !!token;
  }

  userProfile = {
    userId: [''],
    email: [''],
    address: [''],
    phoneNumber: [''],
    pincode: ['']
  };

  data: Array<Data> = [];
  totalDonation = 0;

  ngOnInit(): void {
    this.decoded = this.api.getToken()
    console.log(this.decoded)
    let email = this.decoded['userEmail']

    this.edituserprofile.getById(email).subscribe(
      (data) => {
        this.userProfile = data;
      },
      (error) => {
        console.error('Error fetching profile', error);
      }

    );

    this.http.get("https://philanthrolink.stackroute.io/paymentsave/getAllPayment").subscribe(
      (response) => {
        console.log(response)
        console.log(typeof response)
        for (let i = 0; i < Object.keys(response).length; i++) {
          if (Object.values(response)[i].userData.email == this.userProfile?.email) {
            this.data.push(Object.values(response)[i]);
            this.totalDonation = this.totalDonation + parseInt(Object.values(response)[i].amount);
          }
        }
        this.data = this.data.reverse();
        console.log(this.data);
      },
      (error) => {
        console.log(error)
      }
    );
    const storedUserRole = localStorage.getItem('Role');
    if (storedUserRole != null) {
      this.userRole = storedUserRole;
    }
    this.buttonRemoveBasedOnUser();
  }
  openDialog() {
    this.matDialog.open(DialogbodyComponent, {
      width: "900px",
      // height:"600px"
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

  timeStampToDate(dateString: string): string {
    const dateParts = dateString.split('.');
    if (dateParts.length !== 6) {
      return 'Invalid Date';
    }

    const day = dateParts[2];
    const monthIndex = parseInt(dateParts[1], 10) - 1;
    const year = dateParts[0];

    const months = [
      'January', 'February', 'March',
      'April', 'May', 'June',
      'July', 'August', 'September',
      'October', 'November', 'December'
    ];

    const formattedDate = `${day} ${months[monthIndex]} ${year}`;
    return formattedDate;
  }

  timeStampToTime(timeString: string): string {
    const timeParts = timeString.split('.');
    if (timeParts.length !== 6) {
      return 'Invalid Time';
    }

    const hours = parseInt(timeParts[3], 10);
    const minutes = parseInt(timeParts[4], 10);

    const period = hours >= 12 ? 'pm' : 'am';
    const formattedHours = hours % 12 || 12;

    const formattedTime = `${formattedHours}:${minutes.toString().padStart(2, '0')} ${period}`;
    return formattedTime;
  }

  saveProfile() {
    console.log(this.userProfile)
    this.edituserprofile.putById(this.userProfile).subscribe(
      (response) => {
        console.log("Update successful", response)
      },
      (error) => {
        console.log("Error", error)
      }
    );

    this.router.navigate(['/']);
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

