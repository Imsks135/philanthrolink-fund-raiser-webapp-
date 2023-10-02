import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ChataiComponent } from '../chatai/chatai.component';


@Component({
  selector: 'app-chatbot-icon',
  templateUrl: './chatbot-icon.component.html',
  styleUrls: ['./chatbot-icon.component.css']
})
export class ChatbotIconComponent {

  constructor(private dialog: MatDialog) {}

  openChatbotPopup(): void {
    this.dialog.open(ChataiComponent, {
      width: '400px',
      height: '300px' // Set the width as per your requirements
      // Add other dialog configuration options here
    });
  }
}
