package com.stackroute.paymentservice.paymentService;

import com.stackroute.paymentservice.repository.DBRepository;
import com.stackroute.paymentservice.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Service
public class DBService
{
	@Autowired
	private DBRepository dbRepository;

	public Payment savePayment(Payment payment)
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		payment.setTimeStamp(timeStamp);
		return dbRepository.save(payment);
	}

	public Payment getPayment(String paymentId)
	{
		Optional<Payment> optional = dbRepository.findById(paymentId);
		Payment payment = optional.get();
		return payment;
	}

	public Payment updatePayment(Payment payment, String paymentId)
	{
		payment.setPaymentId(paymentId);
		return dbRepository.save(payment);
	}

	public void deletePayment(String paymentId)
	{
		dbRepository.deleteById(paymentId);
	}

	public List<Payment> getAllPayment()
	{
		return dbRepository.findAll();
	}
}
