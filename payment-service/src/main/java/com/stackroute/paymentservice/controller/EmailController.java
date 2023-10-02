package com.stackroute.paymentservice.controller;

import com.stackroute.paymentservice.model.Payment;
import com.stackroute.paymentservice.producer.PaymentDTO;
import org.json.simple.JSONObject;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("/paymentemail")
public class EmailController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private DirectExchange directExchange;

    @PostMapping("/sendEmail")
    public void sendEmail(@RequestBody Payment payment) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		payment.setTimeStamp(timeStamp);

        System.out.println("Sending Email to " + payment.getUserData().get("email"));

        JSONObject paymentObj = new JSONObject();

		paymentObj.put("userEmail", payment.getUserData().get("email"));
		paymentObj.put("subject", "Philanthrolink - Payment Successful");
		paymentObj.put("message", "Dear " + payment.getUserData().get("name") + ",\n" +
				"\n" +
				"We are thrilled to inform you that your recent donation has been successfully processed. On behalf of Philanthrolink, we want to express our deepest gratitude for your generosity and commitment to our cause.\n" +
				"\n" +
				"Donation Details:\n" +
				"\n" +
				"Donation Amount: " + payment.getAmount() + "\n" +
				"Donation Date and Time: " + payment.getTimeStamp() + "\n" +
				"Transaction ID: " + payment.getRazorpayOrderId() + "]\n" +
                "\n" +
				"Warm regards,\n" +
				"\n" +
				"Philanthrolink\n");

		PaymentDTO paymentDTO = new PaymentDTO(paymentObj);
		rabbitTemplate.convertAndSend("PhilanthrolinkExchange", "PaymentRoutingKey", paymentDTO);
    }
}
