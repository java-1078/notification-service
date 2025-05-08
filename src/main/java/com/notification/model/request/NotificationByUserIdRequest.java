package com.notification.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NotificationByUserIdRequest {

    @NotBlank(message="userId is mandatory")
    private String userId;

    @NotBlank(message="Subject is mandatory")
    private String subject;

    @NotBlank(message="message is mandatory")
    private String message;
}
