import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class ServiceService {

  constructor(private http:HttpClient) {}
  getfeedbacks(){
    return this.http.get("https://philanthrolink.stackroute.io/api/v1/GetFeedBacks");
  }
  postfeedbacks(feedback:any){
    return this.http.post("https://philanthrolink.stackroute.io/api/v1/FeedBack",feedback);
  }
}
