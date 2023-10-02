import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginFormComponent } from '../log-in/log-in.component';
function _window():any{
  return window;
}
@Injectable({
  providedIn: 'root'
})
export class LoginService {
  constructor(private httpClient: HttpClient) { }
  baseUrl: string = "https://philanthrolink.stackroute.io/api/v1/user";
  get nativeWindow():any{
    return _window();
  }
  login(loginData: any) {
    return this.httpClient.post(this.baseUrl + "/na/login", loginData,{responseType: 'json'});
  }
  loggedIn: boolean = false;
  isLoggedIn(): void {
    this.loggedIn = true;
  }
  logout(): void {
    localStorage.removeItem('Token');
    this.loggedIn = false;
  }
  getLoginStatus(): boolean {
    return this.loggedIn;
  }
}
