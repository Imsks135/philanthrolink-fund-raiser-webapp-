import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegistrationFormComponent } from './registration/registration.component';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DonationPageComponent } from './donation-page/donation-page.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatMenuModule} from '@angular/material/menu';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatDialogModule} from '@angular/material/dialog';
import { DialogbodyComponent } from './donation-page/dialogbody/dialogbody.component';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import {MatCardModule} from '@angular/material/card';
import { FlexLayoutModule } from '@angular/flex-layout';
import { LoginFormComponent } from './log-in/log-in.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { FeedbackComponent } from './feedback/feedback.component';
import { ReviewsComponent } from './reviews/reviews.component';
import { PaymentComponent } from './payment/payment.component';
import { PaymentDialogSuccessComponent } from './payment/payment-dialog-success/payment-dialog-success.component';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { EditProfileComponent } from './edit-profile/edit-profile.component';
import { MyCampaignComponent } from './donation-page/my-campaign/my-campaign.component';
import { ChataiComponent } from './chatbot/chatai/chatai.component';
import { ChatbotIconComponent } from './chatbot/chatbot-icon/chatbot-icon.component';
import { JwtModule } from "@auth0/angular-jwt";



@NgModule({
  declarations: [
    MyCampaignComponent,
    ChataiComponent,
    EditProfileComponent,
    FeedbackComponent,
    AppComponent,
    LoginFormComponent,
    ReviewsComponent,
    PaymentComponent ,
    RegistrationFormComponent,
    DonationPageComponent,
    DialogbodyComponent,
    PaymentDialogSuccessComponent,
    ChataiComponent,
    ChatbotIconComponent,
    FeedbackComponent,
    ReviewsComponent,
  ],
  imports: [
    MatMenuModule,
    BrowserModule,
    MatSnackBarModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatCardModule,
    FlexLayoutModule,
    HttpClientModule,
    JwtModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
