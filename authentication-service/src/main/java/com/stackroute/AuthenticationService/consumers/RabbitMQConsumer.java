//package com.example.AuthenticationService.consumers;
//
//import com.example.AuthenticationService.AuthenticationService;
//import org.json.JSONObject;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class RabbitMQConsumer {
//
//    AuthenticationService authenticationService;
//    @RabbitListener(queues = "AuthQueue")
//    public void handleMessage(JSONObject jsonObject) {
//        // Process the incoming message
//        System.out.println("----------------------------------------");
//        System.out.println("Login Successful " +jsonObject.toString());
//
//    }
//}
