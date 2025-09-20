package com.cloud_kafka_notification.api.dto.converted;

import com.cloud_kafka_notification.notification.event.NotificationType;
import java.time.Instant;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ConvertedLikeNotification extends ConvertedNotification{

    private final String userName;
    private final String userProfileImageUrl;
    private final long userCount;
    private final String postImageUrl;

    @Builder
    public ConvertedLikeNotification(String id,
            NotificationType type,
            Instant occurredAt, Instant lastUpdatedAt, String userName,
            String userProfileImageUrl, long userCount, String postImageUrl) {
        super(id, type, occurredAt, lastUpdatedAt);
        this.userName = userName;
        this.userProfileImageUrl = userProfileImageUrl;
        this.userCount = userCount;
        this.postImageUrl = postImageUrl;
    }
}
