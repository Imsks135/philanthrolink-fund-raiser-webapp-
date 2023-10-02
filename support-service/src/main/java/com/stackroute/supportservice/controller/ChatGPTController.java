package com.stackroute.supportservice.controller;

import com.stackroute.supportservice.model.ChatBotInputRequest;
import com.stackroute.supportservice.model.ChatGPTResponse;
import com.stackroute.supportservice.service.ChatGPTService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/chatgpt")
public class ChatGPTController {

    private final ChatGPTService chatGPTService;

    public ChatGPTController(ChatGPTService chatGPTService) {
        this.chatGPTService = chatGPTService;
    }

    @PostMapping("/chat")
    public ResponseEntity<ChatGPTResponse> processInputRequest(@RequestBody ChatBotInputRequest chatbotInputRequest) {
        ChatGPTResponse chatCPTResponse = chatGPTService.getChatCPTResponse(chatbotInputRequest.getMessage());
        return new ResponseEntity<>(chatCPTResponse, HttpStatus.OK);
    }

}
