package com.stackroute.UserProfileService.controller;
import java.util.List;
import java.util.Optional;

import com.stackroute.UserProfileService.exception.UserAlreadyExistsException;
import com.stackroute.UserProfileService.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitTemplateConfigurer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.stackroute.UserProfileService.*;
import com.stackroute.UserProfileService.model.*;
import com.stackroute.UserProfileService.model.User;

import com.stackroute.UserProfileService.repository.*;
import com.stackroute.UserProfileService.service.*;


@RestController
@RequestMapping("/user")

public class UserController{
	@Autowired
    UserService userService;
    
    @PostMapping("/na/signup")
    public User signup(@RequestBody User user) throws UserAlreadyExistsException {
//    	System.out.println(user);
    	return this.userService.registerUser(user);
    	
    	
    }
    
    @GetMapping("/na/getAll")
    public List<User> getallusers(){
    	return this.userService.getUser();
    }


//    @GetMapping("/getUserByEmail/{email}")
//    public ResponseEntity<?> getUserByEmail(@PathVariable("email") String userEmail) {
//        try {
//            User user = iUserService.getUserByEmail(userEmail);
//            return new ResponseEntity<>(user, HttpStatus.OK);
//        } catch (UserNotFoundException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//        }
//    }
    @GetMapping("/na/getByEmail/{email}")
    public Optional<User> getUserByEmail(@PathVariable("email") String userEmail) throws UserNotFoundException {

        return Optional.ofNullable(this.userService.getByEmail(userEmail));
    }
    
    @PutMapping("na/update")
    public User UpdateUser(@RequestBody User user) {
    	return this.userService.updateUser(user);
    }
      
}