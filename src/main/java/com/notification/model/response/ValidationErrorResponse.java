package com.notification.model.response;


import lombok.Data;

import java.util.Map;

@Data
public class ValidationErrorResponse {
    private String status;
    private String message;
    private Map<String,String> errors;
}
