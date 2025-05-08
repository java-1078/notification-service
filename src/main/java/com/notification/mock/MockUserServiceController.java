package com.notification.mock;

import com.notification.model.response.UserContactResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mock/users")
public class MockUserServiceController {

    @GetMapping("/getUserDetails/{userId}")
    public UserContactResponse getUserDetails(@PathVariable Long userId) {
        UserContactResponse response = new UserContactResponse();
        response.setEmail("testuser@example.com");
        response.setPhoneNumber("+15551234567");
        response.setNotifyType("BOTH");
        return response;
    }
}
