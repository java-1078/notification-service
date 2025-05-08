package com.notification.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotifyDTO {

    private String userId;
    private String email;
    private String phoneNumber;
    private String notifyType;
    private String subject;
    private String message;

}
