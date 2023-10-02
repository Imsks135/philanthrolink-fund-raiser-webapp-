import { Injectable } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { map, Observable } from 'rxjs';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root'
})
export class SharedService {
  helper = new JwtHelperService();
  decodedToken: any
  token: any

  constructor(private http: HttpClient) { }

  postCampaign(data: any) {
    return this.http.post<any>("https://philanthrolink.stackroute.io/campaigns/addNew", data)
      .pipe(map((res: any) => {
        return res;
      }));
  }

  getCampaigns(){
    return this.http.get("https://philanthrolink.stackroute.io/campaigns/getall")
      .pipe(map((res: any) => {
        return res;
      }))
  }

  getCampignsByName(name: string): Observable<any>{
    return this.http.get<any>(`https://philanthrolink.stackroute.io/campaigns/getbyname/${name}`)
    .pipe(map((res:any)=>{
      return res;
    }))

  }

  getCampaignsByCategory(category: string): Observable<any>{
    return this.http.get<any>(`https://philanthrolink.stackroute.io/campaigns/category/${category}`)
    .pipe(map((res: any)=>{
      return res;
    }))
  }
  getToken(): string | null {
    // Retrieve the token from local storage
    this.token = localStorage.getItem('Token');
    this.decodedToken = this.helper.decodeToken(this.token);
    return this.decodedToken
  }

  getDtabyEmail(email: string): Observable<any>{
    return this.http.get<any>(`https://philanthrolink.stackroute.io/campaigns/email/${email}`)
    .pipe(map((res: any)=>{
      return res;
    }))
  }

  deleteCampaign(id: number){
    return this.http.delete<any>("https://philanthrolink.stackroute.io/campaigns/remove/"+id)
    .pipe(map((res: any)=>{
      return res;
    }))
  }

  UpdateProject(data : any, id:number){
    return this.http.put<any>("https://philanthrolink.stackroute.io/campaigns/update/"+id, data)
    .pipe(map((res: any)=>{
      return res;
    }))

  }
}
