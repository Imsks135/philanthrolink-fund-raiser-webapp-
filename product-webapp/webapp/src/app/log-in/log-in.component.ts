import { Component } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { LoginService } from '../Services/login.service';

@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.css']
})
export class LoginFormComponent {
  showSuccessDialog: boolean = false;
  showSnackbar: boolean = false;
  snackbarMessage: string = '';
  loginForm: FormGroup;
  responseData: any;
  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private snackbar: MatSnackBar,
    private loginService: LoginService
  ) {
    this.loginForm = this.formBuilder.group({
      userEmail: new FormControl('', [Validators.required, Validators.email]),
      userPassword: ['', Validators.required],
    });
  }
  closeSuccessDialog(): void {
    this.showSuccessDialog = false;
  }
  closeSnackbar(): void {
    this.showSnackbar = false; // Close the snack bar
  }
  loginCheck() {
    // alert("entered login check");
    console.log(this.loginForm.value)
    this.loginService.login(this.loginForm.value).subscribe(
      response => {
        this.responseData = response;
        console.log(this.responseData)
        console.log(this.responseData.Token);
        localStorage.setItem('Token', this.responseData.Token);
        localStorage.setItem('Role',this.responseData.userRole)
        this.loginService.isLoggedIn();
        const userRole = this.responseData.userRole;
        if (userRole === 'Donor') {
          this.snackbar.open('You have successfully logged in as a Donour!', 'Ok', {
            duration: 2000,
          });
          this.router.navigate(['dashboard']);
        } else if (userRole === 'FundRaiser') {
          this.snackbar.open('You have successfully logged in as a Fund Raiser!', 'Ok', {
            duration: 2000,
          });
          this.router.navigate(['dashboard']);
        } else {
          this.snackbar.open('Invalid User, Please try again!', 'Ok', {
            duration: 6000,
          });
          alert('Invalid user role');
        }
      },
      error => {
        alert('Invalid user');
        this.snackbar.open('Invalid User, Please try again!', 'Ok', {
          duration: 6000,
        });
      }
    );
  }
  home() {
    this.router.navigateByUrl("home");
  }
  back() {
    this.router.navigateByUrl("home");
  }
  register() {
    this.router.navigateByUrl('register');
  }
  get userEmail() {
    return this.loginForm.get("userEmail");
  }
  get userPassword() {
    return this.loginForm.get("userPassword");
  }
}