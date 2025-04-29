package com.notification.service.impl;

import com.notification.model.request.PaymentRequest;
import com.notification.model.request.UserIdRequest;
import com.notification.model.request.UserRequest;
import com.notification.model.response.UserContactResponse;
import com.notification.model.response.UserResponse;
import com.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private RestTemplate restTemplate;

    public UserResponse userNotification(UserRequest userRequest){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(userRequest.getEmailId());
        message.setSubject(userRequest.getSubject());
        message.setText(userRequest.getBody());

        mailSender.send(message);

        UserResponse userResponse = new UserResponse();
        userResponse.setStatus("SUCCESS");
        userResponse.setMessage("Email sent to "+ userRequest.getUserName());
        return userResponse;

    }

    public UserResponse paymentNotification(PaymentRequest paymentRequest){

        UserIdRequest userIdRequest = new UserIdRequest();
        userIdRequest.setUserId(paymentRequest.getUserId());


        String ServiceUrl="http://localhost:8080/mock/users/getUserDetails";
        UserContactResponse userContactResponse=restTemplate.postForObject(ServiceUrl,userIdRequest,UserContactResponse.class);

        if (userContactResponse==null || userContactResponse.getNotifyType()== null){
            UserResponse userResponse = new UserResponse();
            userResponse.setStatus("FAILURE");
            userResponse.setMessage("User not found or notification type missing");
        }

        String notifyType = userContactResponse.getNotifyType().toUpperCase();

        if(notifyType.equals("EMAIL") || notifyType.equals("BOTH")){
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(userContactResponse.getEmail());
            mail.setText(paymentRequest.getMessage());
            mailSender.send(mail);
        }

        if(notifyType.equals("SMS") || notifyType.equals("BOTH")){
            System.out.printf("SMS sent to %s: %s%n", userContactResponse.getPhoneNumber(),paymentRequest.getMessage());
        }
        UserResponse userResponse = new UserResponse();
        userResponse.setStatus("SUCCESS");
        userResponse.setMessage("Notification sent via " + notifyType);
        return userResponse;
    }

}
