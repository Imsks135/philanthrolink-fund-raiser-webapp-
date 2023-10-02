package com.stackroute.emailservice.controller;
import netscape.javascript.JSObject;
import org.json.JSONObject;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.stackroute.emailservice.model.EmailMessage;
import com.stackroute.emailservice.service.EmailService;


@RestController
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;


    EmailMessage emailMessage;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;

    }

    @PostMapping("/send")
    public void sendEmail(@RequestBody EmailMessage emailMessage) {
        System.out.println("----------------------------------------");
        System.out.println(emailMessage);
        emailService.sendEmail(emailMessage);
    }



}
