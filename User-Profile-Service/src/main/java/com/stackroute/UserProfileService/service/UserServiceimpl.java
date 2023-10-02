package com.stackroute.UserProfileService.service;
import java.util.List;
import java.util.Optional;


import com.stackroute.UserProfileService.exception.UserNotFoundException;
import com.stackroute.UserProfileService.producer.UserDTO;
import org.json.simple.JSONObject;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.UserProfileService.controller.UserController;
import com.stackroute.UserProfileService.exception.UserAlreadyExistsException;
import com.stackroute.UserProfileService.model.*;
import com.stackroute.UserProfileService.repository.UserRepository;
import com.stackroute.UserProfileService.repository.UserRepository.*;


@Service
public class UserServiceimpl implements UserService{

	@Autowired
	UserRepository userRepository;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private DirectExchange directExchange;
	

	
	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	@Override
	public List<User> getUser() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public String deleteUser(String email) {
		// TODO Auto-generated method stub
		User del = userRepository.findById(email).get();
		userRepository.delete(del);
		return "Deleted";
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}
//	@Autowired
//	UserRepository userRepository;

	@Override
	public User getByEmail(String email) throws UserNotFoundException {
//		return userRepository.findById(email).get();
		Optional<User> userOptional = userRepository.findByEmail(email);
		if (userOptional.isPresent()) {
			return userOptional.get();
		} else {
			throw new UserNotFoundException("User not found!");
		}
	}
	
	
	@Override
	 public User registerUser(User user) throws UserAlreadyExistsException {
		Optional<User> userOptional = userRepository.findByEmail(user.getEmail());

	     if (userOptional.isPresent()) {
			 System.out.println(userOptional.get());
	         throw new UserAlreadyExistsException("User Already Exists!");
	     } else {
	         JSONObject authobj = new JSONObject();
	 
	         authobj.put("userEmail", user.getEmail());
	         authobj.put("userPassword", user.getPassword());
	         authobj.put("userRole", user.getRole());
	         
	 
	         JSONObject emailObj = new JSONObject();
	         emailObj.put("userEmail", user.getEmail());
	         emailObj.put("userName", user.getUserName());
	 
	         UserDTO authDTO = new UserDTO(authobj);
	         rabbitTemplate.convertAndSend(directExchange.getName(), "thisIsAuthKey", authDTO);

	         UserDTO emailDTO = new UserDTO(emailObj);
	         rabbitTemplate.convertAndSend(directExchange.getName(),"thisIsEmailKey",emailDTO);
//	 
	         User savedUser = userRepository.save(user);
	       return savedUser;
	     }

	 }

//	@Override
//	public updateUser (User user) {
//		return userRepository.save(user);
//	}

	
	
	
////	public User findByEmailAndSecQuestionAndSecAnswer(String email,String secQuestion,String secAnswer) {
////	
////		return userRepository.findByEmailAndSecQuestionAndSecAnswer(email, secQuestion, secAnswer);
////	} }
}


