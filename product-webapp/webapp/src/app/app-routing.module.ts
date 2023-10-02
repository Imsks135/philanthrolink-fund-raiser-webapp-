import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DonationPageComponent } from './donation-page/donation-page.component';
import { PaymentComponent } from './payment/payment.component';
import { LoginFormComponent } from './log-in/log-in.component';
import { RegistrationFormComponent } from './registration/registration.component';
import { FeedbackComponent } from './feedback/feedback.component';
import { ReviewsComponent } from './reviews/reviews.component';
import { EditProfileComponent } from './edit-profile/edit-profile.component';
import { MyCampaignComponent } from './donation-page/my-campaign/my-campaign.component';
import { ChataiComponent } from './chatbot/chatai/chatai.component';
const routes: Routes = [
  {
    path:"login",component:LoginFormComponent
  },
  {
    path:"registration",component:RegistrationFormComponent
  },
  {
    path:"payment",component:PaymentComponent
  },
  {
    path:"feedback",component:FeedbackComponent
  },
  {
    path:"reviews",component:ReviewsComponent
  },
  {
     path:"chatbot",component:ChataiComponent
  },
  {
    path:"dashboard", component:DonationPageComponent
  },
  {
    path:"EditP", component:EditProfileComponent
  },
  {
    path:"myCampiagn", component: MyCampaignComponent
  },
  {
    path:'', component: DonationPageComponent
  }
]


@NgModule({
  imports: [RouterModule.forRoot(routes,{useHash:true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
