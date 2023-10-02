package com.stackroute.paymentservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;

@Document
public class Payment {

    @Id
    private String paymentId;
    private String amount;
    private String razorpayOrderId;
    private String status;

    public HashMap<String, String> getDonationData() {
        return donationData;
    }

    public void setDonationData(HashMap<String, String> donationData) {
        this.donationData = donationData;
    }

    private HashMap<String, String> donationData;

    private HashMap<String, String> userData;

    private String timeStamp;


    public Payment(String paymentId, HashMap<String, String> userData, String amount, String razorpayOrderId, String status, HashMap<String, String> donationData, String timeStamp)
    {
        super();
        this.paymentId = paymentId;
        this.userData = userData;
        this.amount = amount;
        this.razorpayOrderId = razorpayOrderId;
        this.status = status;
        this.donationData = donationData;
        this.timeStamp = timeStamp;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getRazorpayOrderId() {
        return razorpayOrderId;
    }

    public void setRazorpayOrderId(String razorpayOrderId) {
        this.razorpayOrderId = razorpayOrderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public HashMap<String, String> getUserData() {
        return userData;
    }

    public void setUserData(HashMap<String, String> userData) {
        this.userData = userData;
    }
}

