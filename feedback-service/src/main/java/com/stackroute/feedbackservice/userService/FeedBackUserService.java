package com.stackroute.feedbackservice.userService;

import com.stackroute.feedbackservice.model.User;
import com.stackroute.feedbackservice.repository.FeedBackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedBackUserService {
    private FeedBackRepository repo;
    @Autowired
    public FeedBackUserService(FeedBackRepository repo) {
        this.repo = repo;
    }
    public User saveMessage(User u){
        return repo.save(u);
    }
    public List<User> getMessages(){
        List<User> ulist = repo.findAll();
        return ulist;
    }
}
