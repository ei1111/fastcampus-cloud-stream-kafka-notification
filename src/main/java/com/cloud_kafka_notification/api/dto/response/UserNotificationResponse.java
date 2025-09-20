package com.cloud_kafka_notification.api.dto.response;

import com.cloud_kafka_notification.api.dto.converted.ConvertedCommentNotification;
import com.cloud_kafka_notification.api.dto.converted.ConvertedFollowNotification;
import com.cloud_kafka_notification.api.dto.converted.ConvertedLikeNotification;
import com.cloud_kafka_notification.api.dto.converted.ConvertedNotification;
import com.cloud_kafka_notification.notification.event.NotificationType;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class UserNotificationResponse {

    private String id;
    private NotificationType type;
    private Instant occurredAt;

    public static UserNotificationResponse of(ConvertedNotification notification) {
        switch (notification.getType()) {
            case COMMENT -> {return CommentUserNotificationResponse.of((ConvertedCommentNotification) notification);}
            case LIKE -> {return LikeUserNotificationResponse.of((ConvertedLikeNotification) notification);}
            case FOLLOW -> {return FollowUserNotificationResponse.of((ConvertedFollowNotification) notification);}
            default -> throw new IllegalArgumentException("Invalid notification type");
        }
    }
}
