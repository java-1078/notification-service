package com.notification.service;

import com.notification.model.request.NotificationByUserIdRequest;
import com.notification.model.request.NotificationRequest;
import com.notification.model.response.NotificationResponse;

public interface NotificationService {
    NotificationResponse processNotification(NotificationRequest notificationRequest);
    NotificationResponse processNotification(NotificationByUserIdRequest notificationByUserIdRequest);


}
