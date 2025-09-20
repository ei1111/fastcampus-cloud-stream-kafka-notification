package com.cloud_kafka_notification.api.dto.response;

import com.cloud_kafka_notification.api.dto.converted.ConvertedLikeNotification;
import com.cloud_kafka_notification.notification.event.NotificationType;
import java.time.Instant;
import lombok.Getter;

@Getter
public class LikeUserNotificationResponse extends UserNotificationResponse {

    private final String userName;
    private final String userProfileImage;
    private final long userCount;
    private final String postImageUrl;

    public LikeUserNotificationResponse(String id,
            NotificationType type,
            Instant occurredAt, String userName, String userProfileImage, long userCount,
            String postImageUrl) {
        super(id, type, occurredAt);
        this.userName = userName;
        this.userProfileImage = userProfileImage;
        this.userCount = userCount;
        this.postImageUrl = postImageUrl;
    }

    public static LikeUserNotificationResponse of(ConvertedLikeNotification notification) {
        return new LikeUserNotificationResponse(
                notification.getId(),
                notification.getType(),
                notification.getOccurredAt(),
                notification.getUserName(),
                notification.getPostImageUrl(),
                notification.getUserCount(),
                notification.getPostImageUrl()
        );
    }
}
