package com.cloud_kafka_notification.notification.service;

import com.cloud_kafka_notification.notification.event.Notification;
import com.cloud_kafka_notification.notification.event.NotificationType;
import com.cloud_kafka_notification.notification.repository.NotificationRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationRemoveService {

    private final NotificationRepository notificationRepository;

    public void  deleteById(String id) {
        log.info("Deleting notification with id {}", id);
        notificationRepository.deleteById(id);
    }
}
