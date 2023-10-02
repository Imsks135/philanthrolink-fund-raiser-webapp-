import { Component, OnInit } from '@angular/core';
import { reviews } from 'src/reviews';
import { ServiceService } from '../service.service';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { DialogbodyComponent } from '../donation-page/dialogbody/dialogbody.component';

@Component({
  selector: 'app-reviews',
  templateUrl: './reviews.component.html',
  styleUrls: ['./reviews.component.css']
})
export class ReviewsComponent implements OnInit {
  isLoggedIn: boolean = false;
  shouldShowButton: boolean = true;
  showEditProfile: boolean = true;
  userRole !: string;
  constructor(private ser:ServiceService, private router: Router, private matDialog: MatDialog){
    const token = localStorage.getItem('Token');
    this.isLoggedIn = !!token;
  }
  rev:reviews[]=[]
ngOnInit(): void {
  this.ser.getfeedbacks().subscribe((data:any)=>{
console.log(data)
    this.rev=data;
    
    }
    
  )
  const storedUserRole = localStorage.getItem('Role');
  if (storedUserRole != null) {
    this.userRole = storedUserRole;
  }
  this.buttonRemoveBasedOnUser();
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
openDialog() {
  this.matDialog.open(DialogbodyComponent, {
    width: "900px",
    // height:"600px"
  })
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
