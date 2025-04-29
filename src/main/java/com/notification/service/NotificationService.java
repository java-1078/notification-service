package com.notification.service;

import com.notification.model.request.PaymentRequest;
import com.notification.model.request.UserRequest;
import com.notification.model.response.UserResponse;

public interface NotificationService {
    UserResponse userNotification(UserRequest userRequest);
    UserResponse paymentNotification(PaymentRequest paymentRequest);
}
