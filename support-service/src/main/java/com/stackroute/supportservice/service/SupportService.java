package com.stackroute.supportservice.service;

import com.stackroute.supportservice.exception.SupportRequestNotFoundException;
import com.stackroute.supportservice.model.SupportRequest;
import com.stackroute.supportservice.repository.SupportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SupportService {

    @Autowired
    private SupportRepository supportRepository;

    public SupportRequest createSupportRequest(SupportRequest supportRequest) {
        return supportRepository.save(supportRequest);
    }

    public List<SupportRequest> getAllSupportRequests() {
        return supportRepository.findAll();
    }
    public List<SupportRequest> getSupportRequestsByUser(String userId) {
        return supportRepository.findByUserId(userId);
    }

    public Optional<SupportRequest> getSupportRequestById(Long id)
    {
        return supportRepository.findById(id);
    }

    public SupportRequest updateSupportRequest(Long id, SupportRequest updatedRequest) {
        Optional<SupportRequest> optionalSupportRequest = supportRepository.findById(id);

        if (optionalSupportRequest.isPresent()) {
            SupportRequest supportRequest = optionalSupportRequest.get();
            supportRequest.setStatus(updatedRequest.getStatus());
            supportRequest.setResponse(updatedRequest.getResponse());
            return supportRepository.save(supportRequest);
        } else {
            throw new SupportRequestNotFoundException("Support request with id " + id + " not found.");
        }
    }
}
