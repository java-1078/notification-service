package com.notification.model.response;

import lombok.Data;

@Data
public class NotificationResponse {

    private String emailStatus;
    private String smsStatus;
    private String message;

}
