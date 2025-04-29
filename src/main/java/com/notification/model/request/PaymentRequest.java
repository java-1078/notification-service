package com.notification.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PaymentRequest {
    @NotBlank(message="userId is mandatory")
    private String userId;

    @NotBlank(message="message is mandatory")
    private String message;
}
