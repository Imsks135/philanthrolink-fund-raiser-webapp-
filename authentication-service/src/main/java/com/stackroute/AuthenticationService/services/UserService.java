package com.stackroute.AuthenticationService.services;

import com.stackroute.AuthenticationService.model.User;
import com.stackroute.AuthenticationService.model.UserDTO;


public interface UserService {
    
    Void registerUser(UserDTO userDTO);
    User getRole(User user);
}
