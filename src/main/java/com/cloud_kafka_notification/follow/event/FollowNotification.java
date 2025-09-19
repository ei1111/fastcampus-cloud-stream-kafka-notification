package com.cloud_kafka_notification.follow.event;

import com.cloud_kafka_notification.global.util.InstantDateTime;
import com.cloud_kafka_notification.global.util.NotificationIdGenerator;
import com.cloud_kafka_notification.notification.event.Notification;
import com.cloud_kafka_notification.notification.event.NotificationType;
import java.time.Instant;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.TypeAlias;

@Getter
@TypeAlias("FollowNotification")
public class FollowNotification extends Notification {
    //팔로워의 id
    private final Long followerId;

    @Builder
    public FollowNotification(String id, Long userId, NotificationType type, Instant occurredAt,
            Instant createdAt, Instant lastUpdateAt, Instant deletedAt, Long followerId) {
        super(id, userId, type, occurredAt, createdAt, lastUpdateAt, deletedAt);
        this.followerId = followerId;
    }


    public static FollowNotification from(FollowEvent  followEvent) {
        return FollowNotification.builder()
                .id( NotificationIdGenerator.generate())
                .userId(followEvent.getTargetUserId())
                .type(NotificationType.FOLLOW)
                .occurredAt(followEvent.getCreatedAt())
                .createdAt(InstantDateTime.now())
                .lastUpdateAt(InstantDateTime.now())
                .deletedAt(InstantDateTime.deleteDateTime())
                .followerId(followEvent.getUserId())
                .build();
    }
}
