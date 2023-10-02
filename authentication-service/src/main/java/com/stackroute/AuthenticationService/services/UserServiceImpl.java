package com.stackroute.AuthenticationService.services;

import com.stackroute.AuthenticationService.model.User;
import com.stackroute.AuthenticationService.model.UserDTO;
import com.stackroute.AuthenticationService.repositories.UserRepo;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{


    @Autowired
    UserRepo userRepo;

    @RabbitListener(queues = "authQueue")
    @Override
    public Void registerUser(UserDTO userDTO) {

        User user = new User();
        user.setUserEmail(userDTO.getJsonObject().get("userEmail").toString());
        user.setUserPassword(userDTO.getJsonObject().get("userPassword").toString());
        user.setUserRole(userDTO.getJsonObject().get("userRole").toString());

        userRepo.save(user);

        return null;
    }

    @Override
    public User getRole(User user) {

            return userRepo.findByUserEmailAndUserPassword(user.getUserEmail(), user.getUserPassword());

    }

}
