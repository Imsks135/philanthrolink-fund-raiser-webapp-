package com.stackroute.supportservice.repository;

import com.stackroute.supportservice.model.SupportRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupportRepository extends MongoRepository<SupportRequest,Long> {

    List<SupportRequest> findByUserId(String userId);
}
