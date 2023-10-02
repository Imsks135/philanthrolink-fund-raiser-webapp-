package com.stackroute.AuthenticationService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.mongodb.repository.MongoRepository;
// import java.util.Optional;
import com.stackroute.AuthenticationService.model.User;

public interface UserRepo extends JpaRepository<User,String> {

    User findByUserEmail(String userEmail);
    User findByUserEmailAndUserPassword(String userEmail, String userPassword);


}
