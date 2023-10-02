package com.stackroute.AuthenticationService.controller;

import com.stackroute.AuthenticationService.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.AuthenticationService.services.UserService;
import com.stackroute.AuthenticationService.util.JwtUtils;

@RestController
@RequestMapping("api/v1/user")
//@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private JwtUtils jwtUtil;

    @Autowired
    UserService UserService;

    @Autowired
    private AuthenticationManager authenticationManager;

//    @PostMapping("/na/register")
//    public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {
//
//        User user1 = this.UserService.registerUser(user);
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
//
//    }

    @PostMapping("/na/login")
    public ResponseEntity<?> generateToken(@RequestBody User user) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUserEmail(), user.getUserPassword()));
//            return jwtUtil.generateToken(authRequest.getUsername());
            User validUser = UserService.getRole(user);
            return new ResponseEntity<>(jwtUtil.newgenerateToken(validUser), HttpStatus.CREATED);
        } catch (Exception ex) {
            throw new Exception("invalid username/password");
        }
//        return jwtUtil.generateToken(authRequest.getUsername());
    }

}
