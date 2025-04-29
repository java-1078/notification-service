package com.notification.controller;


import com.notification.model.request.PaymentRequest;
import com.notification.model.request.UserRequest;
import com.notification.model.response.UserResponse;
import com.notification.service.NotificationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")

public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/UserNotification")
    public ResponseEntity<UserResponse> userNotification(@Valid @RequestBody UserRequest userRequest){
        UserResponse userResponse = notificationService.userNotification(userRequest);
        return ResponseEntity.ok(userResponse);
    }

    @PostMapping("/PaymentNotification")
    public ResponseEntity<UserResponse> paymentNotification(@Valid @RequestBody PaymentRequest paymentRequest){
        UserResponse userResponse = notificationService.paymentNotification(paymentRequest);
        return ResponseEntity.ok(userResponse);
    }

}
