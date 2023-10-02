package com.stackroute.paymentservice.repository;

import com.stackroute.paymentservice.model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DBRepository extends MongoRepository<Payment,String>
{
	// Define custom queries or use the default CRUD operations provided by MongoRepository
}
