/* eslint-disable @typescript-eslint/no-explicit-any */
import { HttpClient } from '@angular/common/http';
import { Component, ViewChild, TemplateRef, HostListener, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { OrderServiceService } from './order-service.service';
import { PaymentDialogSuccessComponent } from './payment-dialog-success/payment-dialog-success.component';
import { DataService } from '../donation-page/data.service';
import { EditProfileService } from '../Services/edit-profile.service';

declare let Razorpay: any;

export interface DialogData {
  animal: string;
  name: string;
}

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {
  title = 'demo';

  @ViewChild('dialogTemplate', { static: false }) dialogTemplate!: TemplateRef<any>;

  form: any = {};
  donationData: { name: string; description: string ; Amount: number} | null = null;
  donatedAmount: any;
  userDonatedAmount: any;
  window: any;
  f: any;

  userProfile = {
    userId:[''],
    email: ['h3gqfk58@outlook.com'],
    address: [''],
    phonenumber: [''],
    pincode:['']
  };

  constructor(private http: HttpClient,
    private orderService:OrderServiceService,private dialog: MatDialog, private dtaserv: DataService,
    private edituserprofile: EditProfileService) {

  }

  sayHello() {
    alert("Hello DK");
  }

  paymentId: string | undefined;
  error: string | undefined;

  options = {
    "key": "",
    "amount": "",
    "name": "Philanthrolink",
    "description": "Fund raising Organisation",
    "image": "assets/GOOD_CAUSE-removebg-preview.png",
    "order_id":"",
    "handler": function (response: any){
      const event = new CustomEvent("payment.success",
        {
          detail: response,
          bubbles: true,
          cancelable: true
        }
      );
      window.dispatchEvent(event);
    }
    ,
    "prefill": {
      "name": "",
      "email": "",
      "contact": ""
    },
    "notes": {
      "address": ""
    },
    "theme": {
      "color": "#3399cc"
    }
  };

  onSubmit(): void {
      console.log("pay pressed");

      console.log(this.form.name);

      this.paymentId = '';
      this.error = '';

      this.orderService.createOrder(this.form).subscribe(
      data => {
        this.options.key = data.secretId;
        this.options.order_id = data.razorpayOrderId;
        this.options.amount = data.applicationFee; //paise
        this.options.prefill.name = this.form.name;
        this.options.prefill.email = this.form.email;
        this.options.prefill.contact = this.form.phone;

        const rzp = new Razorpay(this.options);
          rzp.open();

        rzp.on('payment.failed',  (response: { error: { code: any; description: any; source: any; step: any; reason: any; metadata: { order_id: any; payment_id: any; }; }; }) =>{
          // Todo - store this information in the server
          console.log(response);
          console.log(response.error.code);
          console.log(response.error.description);
          console.log(response.error.source);
          console.log(response.error.step);
          console.log(response.error.reason);
          console.log(response.error.metadata.order_id);
          console.log(response.error.metadata.payment_id);
          this.error = response.error.reason;
        }
       );
      }
      ,
      err => {
        this.error = err.error.message;
      }
      );
  }

  @HostListener('window:payment.success', ['$event'])
  onPaymentSuccess(event: { detail: any; }): void {
      this.dialog.open(PaymentDialogSuccessComponent)
       console.log(event.detail);
       const bodyData = {
        "userData" : {
          "id" : this.userProfile.userId[0],
          "name" : this.options.prefill.name,
          "email" : this.options.prefill.email,
          "phoneNumber" : this.options.prefill.contact,
        },
        "donationData" : {
          "id" : "",
          "name" : this.donationData?.name.toString(),
          "description" : this.donationData?.description.toString(),
          "Amount" : this.donationData?.Amount.toString(),
        },
        "timeStamp" : "",
        "amount" : this.options.amount,
        "razorpayOrderId" : this.options.order_id,
        "status" : "success",
      };

      console.log(bodyData)

      this.http.post("https://philanthrolink.stackroute.io/paymentsave/savePayment", bodyData).subscribe(
        (response) => {
          console.log(response)
        },
        (error) => {
          console.log(error)
        }
      );

      this.http.post("https://philanthrolink.stackroute.io/paymentemail/sendEmail", bodyData).subscribe(
        (response) => {
          console.log(response)
        },
        (error) => {
          console.log(error)
        }
      );
  }

    ngOnInit(){
      this.dtaserv.donationData$.subscribe((data) => {
        console.log('Received Data:', data);
        if(data == null) {
          this.donationData = {
            "name" : "Philanthrolink",
            "description" : "Our Platform runs on danations done by people like you. We would be greatfull if you could help us keep this platform free and open for all. However, running a fundraising platform without transaction fees is expensive. We need your help to cover the costs of hosting, security, and maintenance. Even a small donation can make a big difference",
            "Amount" : Infinity
          }
        }
        else {
          this.donationData = data;
        }

        // this.edituserprofile.getById().subscribe(
        //   (data) => {
        //     this.userProfile = data;
        //   },
        //   (error) => {
        //     console.error('Error fetching profile', error);
        //   }
        // );

        this.form.email = this.userProfile.email[0];
        this.form.phone = this.userProfile.phonenumber[0];

        this.userDonatedAmount = 0;
        this.donatedAmount = 0;
        this.http.get("https://philanthrolink.stackroute.io/paymentsave/getAllPayment").subscribe(
          (response) => {
            console.log(response)
            console.log(typeof response)
            for (let i = 0; i < Object.keys(response).length; i++) {
              if(Object.values(response)[i].donationData.name == this.donationData?.name) {
                this.donatedAmount = this.donatedAmount + parseInt(Object.values(response)[i].amount);
                if(Object.values(response)[i].userData.email == this.userProfile?.email[0]) {
                  this.userDonatedAmount = this.userDonatedAmount + parseInt(Object.values(response)[i].amount);
                }
              }
            }
            console.log(this.donatedAmount);
          },
          (error) => {
            console.log(error)
          }
        );
      });
    }
}

