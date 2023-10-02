package com.stackroute.UserProfileService.service;


import com.stackroute.UserProfileService.exception.UserNotFoundException;
import com.stackroute.UserProfileService.model.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.UserProfileService.exception.UserAlreadyExistsException;
import com.stackroute.UserProfileService.model.*;

import com.stackroute.UserProfileService.repository.*;




public interface UserService {
//	public User findByEmailAndSecQuestionAndSecAnswer(String email,String secQuestion,String secAnswer);
	public User saveUser(User user);
	public List<User> getUser();
	public User getByEmail(String email) throws UserNotFoundException;
	public String deleteUser(String email);
	
	User registerUser(User user) throws UserAlreadyExistsException;
	public User updateUser(User user);
}

