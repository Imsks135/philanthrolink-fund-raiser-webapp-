import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EditProfileService {

  constructor(private http: HttpClient) { }

  public getById(email: string):Observable<any>
  {
    const url = `https://philanthrolink.stackroute.io/user/na/getByEmail/${email}`
    return this.http.get<any>(url);
  }

  public putById(userProfile:any):Observable<any>
  {
    const url = 'https://philanthrolink.stackroute.io/user/na/update'
    return this.http.put<any>(url, userProfile);
  }
}
