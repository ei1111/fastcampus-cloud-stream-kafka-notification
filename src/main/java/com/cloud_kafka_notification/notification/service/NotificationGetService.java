package com.cloud_kafka_notification.notification.service;

import com.cloud_kafka_notification.notification.event.Notification;
import com.cloud_kafka_notification.notification.event.NotificationType;
import com.cloud_kafka_notification.notification.repository.NotificationRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationGetService {

    private final NotificationRepository notificationRepository;

    public Optional<Notification> getNotificationByTypeAndCommentId(NotificationType notificationType, Long commentId) {
        return notificationRepository.findByTypeAndCommentId(notificationType, commentId);
    }

    public Optional<Notification> getNotificationByTypeAndPostId(NotificationType notificationType, Long postId) {
        return notificationRepository.findByTypeAndPostId(notificationType, postId);
    }
}
