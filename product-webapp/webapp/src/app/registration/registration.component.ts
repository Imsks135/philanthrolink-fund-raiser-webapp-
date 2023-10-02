import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

import { RegistrationService } from '../Services/registration.service';


@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationFormComponent {
  showSuccessDialog: boolean = false;
  registerForm: FormGroup;
  
  constructor(private formBuilder: FormBuilder, private registerService: RegistrationService,private snackbar: MatSnackBar, private router: Router) {
    this.registerForm = this.formBuilder.group({
      userName: new FormControl('', [Validators.required, Validators.minLength(2)]),
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required, Validators.minLength(5)]),
      confirmPassword: new FormControl('', [Validators.required, Validators.minLength(5)]),

      age: new FormControl('', Validators.required),
      address: new FormControl('', [Validators.required, Validators.minLength(6)]),
      pincode: new FormControl('', [Validators.required, Validators.minLength(6)]),
      phoneNumber: new FormControl('', [Validators.required, Validators.pattern(/^[0-9]{10}$/)]),
      role: new FormControl('', Validators.required)
    });
  }
  

  registerCheck() {
    this.registerService.signUp(this.registerForm.value).subscribe(
      response => {
        console.log(response);
        this.snackbar.open('Registered successfully !!', 'Ok', {
          duration: 6000,
        });
        this.showSuccessDialog=true;
        this.router.navigateByUrl("login");

      },
      error => {
        this.snackbar.open('Please try to register again!!', 'Ok', {
          duration: 6000,
        });
        console.log(error);

      }
    );
  }
  canExit(): boolean {
      const name = this.registerForm.get('userName')?.value;
      const email = this.registerForm.get('userEmail')?.value;
      const password = this.registerForm.get('userPassword')?.value;
      const role = this.registerForm.get('userRole')?.value;
      const address = this.registerForm.get('address')?.value;

      if (name || email || password || role || address) {
        return confirm('Do you want to leave this page?');
      } else {
        return true;
      }
    }

    closeSuccessDialog(): void {
      this.showSuccessDialog = false; // Close the success dialog
    }

  onSubmit() {
    console.log(this.registerForm);
  }
}