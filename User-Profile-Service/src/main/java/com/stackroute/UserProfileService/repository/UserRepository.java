package com.stackroute.UserProfileService.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.UserProfileService.model.*;

import java.util.Optional;

@Repository
	public interface UserRepository extends MongoRepository<User,String> {
	
	    Optional<User>findByEmail(String email);
	   // User findById(String  );
	    
	   //public User findByEmailAndSecQuestionAndSecAnswer(String email,String secQuestion,String secAnswer);

	}


