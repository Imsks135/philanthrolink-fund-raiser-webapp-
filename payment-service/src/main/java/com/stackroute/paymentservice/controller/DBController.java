package com.stackroute.paymentservice.controller;

import com.stackroute.paymentservice.paymentService.DBService;
import com.stackroute.paymentservice.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.core.DirectExchange;

import java.util.List;

@RestController
@RequestMapping("/paymentsave")
public class DBController
{

	@Autowired
	private DBService dbService;

	@PostMapping("/savePayment")
	public Payment savePayment(@RequestBody Payment payment) {
		return dbService.savePayment(payment);
	}

	@GetMapping("/getPayment/{paymentId}")
	public Payment getPayment(@PathVariable String paymentId)
	{
		return dbService.getPayment(paymentId);
	}

	@PutMapping("/updatePayment/{paymentId}")
	public Payment updatePayment(@RequestBody Payment payment, @PathVariable String paymentId)
	{
		return dbService.updatePayment(payment,paymentId);
	}
	
	@DeleteMapping("/deletePayment/{paymentId}")
	public String deletePayment(@PathVariable String paymentId)
	{
		dbService.deletePayment(paymentId);
		return "Deleted Successfully";
	}


	@GetMapping("/getAllPayment")
	public List<Payment> getAllPayment()
	{
		return dbService.getAllPayment();
	}
}
