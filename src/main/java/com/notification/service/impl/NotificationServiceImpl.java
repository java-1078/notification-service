package com.notification.service.impl;

import com.notification.dto.NotifyDTO;
import com.notification.entity.NotificationHistoryEntity;
import com.notification.model.request.NotificationByUserIdRequest;
import com.notification.model.request.NotificationRequest;
import com.notification.model.response.UserContactResponse;
import com.notification.model.response.NotificationResponse;
import com.notification.repository.NotificationHistoryRepository;
import com.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private NotificationHistoryRepository notificationHistoryRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${userServiceUrl}")
    private String userServiceUrl;

    public NotificationResponse processNotification(NotificationRequest notificationRequest){
        NotifyDTO notifyDTO = new NotifyDTO();
        notifyDTO.setUserId(notificationRequest.getUserId());
        notifyDTO.setEmail(notificationRequest.getEmailId());
        notifyDTO.setPhoneNumber(notificationRequest.getPhoneNumber());
        notifyDTO.setNotifyType(notificationRequest.getNotifyType());
        notifyDTO.setSubject(notificationRequest.getSubject());
        notifyDTO.setMessage(notificationRequest.getBody());
        return processNotification(notifyDTO);
    }

    public NotificationResponse processNotification(NotificationByUserIdRequest notificationByUserIdRequest){
        String actualUserServiceURL = userServiceUrl + notificationByUserIdRequest.getUserId();
        UserContactResponse userContactResponse=restTemplate.getForObject(actualUserServiceURL,UserContactResponse.class);

        NotifyDTO notifyDTO = new NotifyDTO();
        notifyDTO.setSubject(notificationByUserIdRequest.getSubject());
        notifyDTO.setMessage(notificationByUserIdRequest.getMessage());

        notifyDTO.setUserId(userContactResponse.getUserId());
        notifyDTO.setEmail(userContactResponse.getEmail());
        notifyDTO.setPhoneNumber(userContactResponse.getPhoneNumber());
        notifyDTO.setNotifyType(userContactResponse.getNotifyType());

        return  processNotification(notifyDTO);
    }


    private NotificationResponse processNotification(NotifyDTO notifyDTO){

        if (!StringUtils.hasText(notifyDTO.getNotifyType())){
            NotificationResponse notificationResponse = new NotificationResponse();
            notificationResponse.setSmsStatus("FAILURE");
            notificationResponse.setEmailStatus("FAILURE");
            notificationResponse.setMessage("User Notify Type is not found");
            return notificationResponse;
        }

        NotificationHistoryEntity entity = new NotificationHistoryEntity();
        entity.setUserId(notifyDTO.getUserId());
        entity.setEmail(notifyDTO.getEmail());
        entity.setPhoneNumber(notifyDTO.getPhoneNumber());
        entity.setNotifyType(notifyDTO.getNotifyType());
        entity.setMessage(notifyDTO.getMessage());

        NotificationResponse notificationResponse = new NotificationResponse();

        String notifyType = notifyDTO.getNotifyType().toUpperCase();
        if(notifyType.equals("EMAIL") || notifyType.equals("BOTH")){
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(notifyDTO.getEmail());
            mail.setSubject(notifyDTO.getSubject());
            mail.setText(notifyDTO.getMessage());
            mailSender.send(mail);
            entity.setEmailSentAt(LocalDateTime.now());
            notificationResponse.setEmailStatus("SUCCESS");
        }

        if(notifyType.equals("SMS") || notifyType.equals("BOTH")){
            System.out.printf("SMS sent to %s: %s%n", notifyDTO.getPhoneNumber(), notifyDTO.getMessage());
            entity.setSmsSentAt(LocalDateTime.now());
            notificationResponse.setSmsStatus("SUCCESS");
        }

        notificationHistoryRepository.save(entity);

        notificationResponse.setMessage("Notification sent via " + notifyType);
        return notificationResponse;
    }
}
