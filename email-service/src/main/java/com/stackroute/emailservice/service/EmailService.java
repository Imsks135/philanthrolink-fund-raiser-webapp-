package com.stackroute.emailservice.service;
import com.stackroute.emailservice.consumers.UserDTO;
import com.stackroute.emailservice.model.AttachmentDTO;
import org.json.simple.JSONObject;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.MimeMessageHelper;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;



import com.stackroute.emailservice.model.EmailMessage;

@Service
public class EmailService {

    private final JavaMailSender emailSender;
    public EmailMessage emailMessage;

    @Autowired
    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
        this.emailMessage = new EmailMessage();

    }

    public void sendEmail(EmailMessage emailMessage) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(emailMessage.getTo());
        mailMessage.setSubject(emailMessage.getSubject());
        mailMessage.setText(emailMessage.getBody());

        emailSender.send(mailMessage);

    }

    public void handleReceivedEmailForUser(UserDTO userDTO) {
        try {
            JSONObject jsonObject = userDTO.getJsonObject();
            String userEmail = (String) jsonObject.get("userEmail");
            String userName = (String) jsonObject.get("userName");


            System.out.println("------------------------");
            if (userEmail != null && userName != null) {
                String subject = "Welcome to Philanthrolink!";
                String body = "Dear " + userName + ",\n\n"
                        + "Thank you for joining our platform. We are thrilled to have you as part of our community.\n\n"
                        + "At Philanthrolink, "
                        + "Best regards,\n"
                        + "The Philanthrolink Team";


                EmailMessage emailMessage = new EmailMessage(userEmail, subject, body);
                sendEmail(emailMessage);
            } else {
                // Handle missing or null properties
                throw new IllegalArgumentException("Missing or null properties in UserDTO");
            }
        } catch (Exception e) {
            // Handle JSON parsing or other exceptions
            e.printStackTrace();
        }


    }

    public void handleReceivedEmailForPayment(UserDTO userDTO) {
        try {
            JSONObject jsonObject = userDTO.getJsonObject();
            String userEmail = (String) jsonObject.get("userEmail");



            System.out.println("------------------------");
            if (userEmail != null) {
                String subject = (String) jsonObject.get("subject");
                String body = (String) jsonObject.get("message");


                EmailMessage emailMessage = new EmailMessage(userEmail, subject, body);
                sendEmail(emailMessage);
            } else {
                // Handle missing or null properties
                throw new IllegalArgumentException("Missing or null properties in UserDTO");
            }
        } catch (Exception e) {
            // Handle JSON parsing or other exceptions
            e.printStackTrace();
        }


    }

    @RabbitListener(queues = "emailQueue2")
    public void receiveMessage(UserDTO userDTO) {
        handleReceivedEmailForUser(userDTO);
    }

    @RabbitListener(queues = "PaymentQueue")
    public void receiveMessagefrompayment(UserDTO userDTO) {
        handleReceivedEmailForPayment(userDTO);
    }




}
