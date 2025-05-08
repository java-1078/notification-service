package com.notification.model.response;

import lombok.Data;

@Data
public class UserContactResponse {
    private String userId;
    private String email;
    private String phoneNumber;
    private String notifyType;
}
