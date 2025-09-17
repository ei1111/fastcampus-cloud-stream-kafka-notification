package com.cloud_kafka_notification.notification.service;

import com.cloud_kafka_notification.notification.event.Notification;
import com.cloud_kafka_notification.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationSaveService {
    private final NotificationRepository notificationRepository;

    public void save(Notification notification) {
      notificationRepository.save(notification);
    }
}
