import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { reviews } from 'src/reviews';
import { DialogbodyComponent } from '../donation-page/dialogbody/dialogbody.component';
import { ServiceService } from '../service.service';

@Component({
  selector: 'app-feedback',
  templateUrl: './feedback.component.html',
  styleUrls: ['./feedback.component.css'],
})
export class FeedbackComponent implements OnInit {
  isLoggedIn: boolean = false;
  userRole !: string;
  shouldShowButton: boolean = true;
  showEditProfile: boolean = true;
  feedbackForm: FormGroup;
  rev:reviews = {
    name:'',
    email:'',
    rating:0,
    feedback:''
  };

  constructor(private formBuilder: FormBuilder, private ser:ServiceService, private matDialog: MatDialog, private router: Router) {
    this.feedbackForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      name: ['', Validators.required],
      rating: ['', Validators.required],
      feedback: ['', Validators.required],

    });
    const token = localStorage.getItem('Token');
    this.isLoggedIn = !!token;
  }
  ngOnInit(): void {
    const storedUserRole = localStorage.getItem('Role');
    if (storedUserRole != null) {
      this.userRole = storedUserRole;
    }
    this.buttonRemoveBasedOnUser();
  }

  // Define the onSubmit method to handle form submission
  onSubmit() {
    if (this.feedbackForm.valid) {
      // You can send the form data to a server or perform other actions here
      console.log(this.feedbackForm.value);
      this.ser.postfeedbacks(this.feedbackForm.value).subscribe((data)=>{
        console.log(data)
      });
      location.reload();
      //this.rev.email=this.feedbackForm.controls['email'];
    }
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

  buttonRemoveBasedOnUser() {
    if (this.userRole !== 'FundRaiser') {
      this.shouldShowButton = false;
    }
    else {
      this.shouldShowButton = true;
    }

  }
}

