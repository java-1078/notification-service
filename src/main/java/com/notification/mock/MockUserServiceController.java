package com.notification.mock;

import com.notification.model.request.UserIdRequest;
import com.notification.model.response.UserContactResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mock/users")
public class MockUserServiceController {

    @PostMapping("/getUserDetails")
    public UserContactResponse getUserDetails(@RequestBody UserIdRequest request) {
        UserContactResponse response = new UserContactResponse();
        response.setEmail("testuser@example.com");
        response.setPhoneNumber("+15551234567");
        response.setNotifyType("BOTH");
        return response;
    }
}
