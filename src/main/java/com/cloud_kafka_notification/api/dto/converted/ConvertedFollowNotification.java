package com.cloud_kafka_notification.api.dto.converted;

import com.cloud_kafka_notification.notification.event.NotificationType;
import java.time.Instant;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ConvertedFollowNotification extends ConvertedNotification{

    private final String userName;
    private final String userProfileImageUrl;
    private final boolean isFollowing;

    @Builder
    public ConvertedFollowNotification(String id, NotificationType type, Instant occurredAt,
            Instant lastUpdatedAt, String userName, String userProfileImageUrl,
            boolean isFollowing) {
        super(id, type, occurredAt, lastUpdatedAt);
        this.userName = userName;
        this.userProfileImageUrl = userProfileImageUrl;
        this.isFollowing = isFollowing;
    }
}
