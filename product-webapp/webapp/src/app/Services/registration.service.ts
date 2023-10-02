import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { RegistrationFormComponent } from '../registration/registration.component';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {



  constructor(private httpClient :HttpClient) { }
  baseUrl:string="https://philanthrolink.stackroute.io/user/";

  signUp(signUpData:any){

    return this.httpClient.post(this.baseUrl+"na/signup",signUpData,{ responseType: 'json' });
  }


}
