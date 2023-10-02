import { Component } from '@angular/core';
import { ChatService } from 'src/app/chat.service';

@Component({
  selector: 'app-chatai',
  templateUrl: './chatai.component.html',
  styleUrls: ['./chatai.component.css']
})
export class ChataiComponent {

  userMessage = '';
  assistantMessages: any[] = [];

  constructor(private chatService: ChatService) {}

  sendMessage() {
    if (this.userMessage.trim() === '') return;

    this.assistantMessages.push({ role: 'user', content: this.userMessage });
    this.userMessage = '';

    this.chatService.sendMessage(this.assistantMessages[this.assistantMessages.length - 1].content).subscribe(
      (response) => {
        this.assistantMessages.push({ role: 'assistant', content: response.choices[0].message.content });
      },
      (error) => {
        console.error('Error sending message:', error);
      }
    );
  }
}