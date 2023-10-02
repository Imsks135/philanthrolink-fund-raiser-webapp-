//package com.stackroute.UserProfileService.producer;
//
//import org.json.JSONObject;
//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//@Component
//
//public class RabbitMQproducer {
//
//
//
//	    private final AmqpTemplate rabbitTemplate;
//
//	    @Autowired
//	    public RabbitMQproducer(AmqpTemplate rabbitTemplate) {
//	        this.rabbitTemplate = rabbitTemplate;
//	    }
//
//	    public String sendMessage() {
//	        // The first parameter is the name of the exchange.
//	        // The second parameter is the routing key.
//	        // The third parameter is the message to send.
//	        JSONObject jsonObject = new JSONObject();
//	        jsonObject.put("to", "pusunurup.ug19.cs@nitp.ac.in");
//	        jsonObject.put("text", "email send successfully!");
//	        jsonObject.put("subject","Sending Email");
//
//	        System.out.println("-------------------------");
//	        String jsonString = jsonObject.toString();
//	        rabbitTemplate.convertAndSend("myExchange", "myRoutingKey", jsonString);
//	        System.out.println("send message:"+jsonObject.toString());
//	        return jsonObject.toString();
//	    }
//	}
//
//
//
