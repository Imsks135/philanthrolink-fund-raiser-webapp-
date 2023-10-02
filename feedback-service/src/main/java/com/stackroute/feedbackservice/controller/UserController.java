package com.stackroute.feedbackservice.controller;

import com.stackroute.feedbackservice.model.User;
import com.stackroute.feedbackservice.userService.FeedBackUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    public FeedBackUserService userService;
    @Autowired
    public UserController(FeedBackUserService userService) {
        this.userService = userService;
    }
//    @CrossOrigin
    @PostMapping("/FeedBack")
    public ResponseEntity<?> SendFeedBack(@RequestBody User user){
        User u1=this.userService.saveMessage(user);
        return new ResponseEntity<User>(u1,HttpStatus.OK);

    }
//    @CrossOrigin
    @GetMapping("/GetFeedBacks")
    public ResponseEntity<?> DisplayFeedBacks(User user){
        List<User> Feedbacks = userService.getMessages();
        return new ResponseEntity<List<User>>(Feedbacks, HttpStatus.OK);
    }
}
