package com.stackroute.feedbackservice.repository;

import com.stackroute.feedbackservice.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedBackRepository  extends MongoRepository<User,String> {
}
