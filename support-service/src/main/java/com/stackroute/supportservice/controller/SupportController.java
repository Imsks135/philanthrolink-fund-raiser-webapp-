package com.stackroute.supportservice.controller;

import com.stackroute.supportservice.model.SupportRequest;
import com.stackroute.supportservice.service.SupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/support")
public class SupportController {
    @Autowired
    private SupportService supportService;

    @GetMapping("/")
    public String getWelcome() {
        return "Welcome to Support-Service";

    }

    @PostMapping
    public ResponseEntity<SupportRequest> createSupportRequest(@RequestBody SupportRequest supportRequest) {
        SupportRequest createdRequest = supportService.createSupportRequest(supportRequest);
        return new ResponseEntity<>(createdRequest, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SupportRequest>> getAllSupportRequests() {
        List<SupportRequest> supportRequests = supportService.getAllSupportRequests();
        return new ResponseEntity<>(supportRequests, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupportRequest> getSupportRequestById(@PathVariable Long id) {
        Optional<SupportRequest> supportRequest = supportService.getSupportRequestById(id);
        return supportRequest.map(request -> new ResponseEntity<>(request, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SupportRequest>> getSupportRequestsByUser(@PathVariable String userId) {
        List<SupportRequest> supportRequests = supportService.getSupportRequestsByUser(userId);
        return new ResponseEntity<>(supportRequests, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupportRequest> updateSupportRequest(@PathVariable Long id,
                                                               @RequestBody SupportRequest updatedRequest) {
        SupportRequest supportRequest = supportService.updateSupportRequest(id, updatedRequest);
        return new ResponseEntity<>(supportRequest, HttpStatus.OK);
    }
}
