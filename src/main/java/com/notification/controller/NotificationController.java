package com.notification.controller;

import com.notification.model.request.NotificationByUserIdRequest;
import com.notification.model.request.NotificationRequest;
import com.notification.model.response.NotificationResponse;
import com.notification.service.NotificationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping
    public ResponseEntity<NotificationResponse> userNotification(@Valid @RequestBody NotificationRequest notificationRequest){
        NotificationResponse notificationResponse = notificationService.processNotification(notificationRequest);
        return ResponseEntity.ok(notificationResponse);
    }

    @PostMapping("/byUserId")
    public ResponseEntity<NotificationResponse> paymentNotification(@Valid @RequestBody NotificationByUserIdRequest notificationByUserIdRequest){
        NotificationResponse notificationResponse = notificationService.processNotification(notificationByUserIdRequest);
        return ResponseEntity.ok(notificationResponse);
    }

}
