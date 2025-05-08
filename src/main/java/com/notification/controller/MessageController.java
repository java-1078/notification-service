package com.notification.controller;

import com.notification.model.response.GreetingResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Value("${message}")
    private String msg;

    @GetMapping("/Greetings")
    public GreetingResponse greetings( ){
        GreetingResponse greetingResponse = new GreetingResponse();
        greetingResponse.setMessage(msg);
        return greetingResponse;
    }

}
