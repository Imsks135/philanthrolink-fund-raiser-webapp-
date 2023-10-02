package com.stackroute.paymentservice.controller;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.stackroute.paymentservice.model.OrderRequest;
import com.stackroute.paymentservice.model.OrderResponse;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
@RequestMapping("/pg")
public class RazorpayController {
	private RazorpayClient client;
	private static final String SECRET_ID = "rzp_test_DWcW3ZMMMtzV2B";
	private static final String SECRET_KEY = "Eu52L1lNmJC4AsNSfBzFwvvY";

	@RequestMapping(path = "/createOrder", method = RequestMethod.POST)
	public OrderResponse createOrder(@RequestBody OrderRequest orderRequest) {
		OrderResponse response = new OrderResponse();
		try {
			client = new RazorpayClient(SECRET_ID, SECRET_KEY);

			Order order = createRazorPayOrder(orderRequest.getAmount());
			System.out.println("---------------------------");
			String orderId = (String) order.get("id");
			System.out.println("Order ID: " + orderId);
			System.out.println("---------------------------");
			response.setRazorpayOrderId(orderId);
			response.setApplicationFee("" + orderRequest.getAmount());
			response.setSecretKey(SECRET_KEY);
			response.setSecretId(SECRET_ID);
			response.setPgName("razor");
			response.setName("Pranav");
			response.setEmail("h3gqfk58q@outlook.com");

			return response;
		} catch (RazorpayException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

	private Order createRazorPayOrder(BigInteger amount) throws RazorpayException {
		JSONObject options = new JSONObject();
		options.put("amount", amount.multiply(new BigInteger("100")));
		options.put("currency", "INR");
		options.put("receipt", "txn_123456");
		options.put("payment_capture", 1); // You can enable this if you want to do Auto Capture.
		return client.orders.create(options);
	}
}
