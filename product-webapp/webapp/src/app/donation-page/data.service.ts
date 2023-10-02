import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DataService {
private donationDataSubject = new BehaviorSubject<{ name: string; description: string; Amount: number } | null>(null);


  constructor() { }

  setDonationData(data: { name: string; description: string; Amount: number }) {
    this.donationDataSubject.next(data);
  }

  get donationData$() {
    return this.donationDataSubject.asObservable();
  }
}
