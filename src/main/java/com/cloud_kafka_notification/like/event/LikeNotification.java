package com.cloud_kafka_notification.like.event;

import com.cloud_kafka_notification.notification.event.Notification;
import com.cloud_kafka_notification.notification.event.NotificationType;
import java.time.Instant;
import java.util.List;
import lombok.Getter;
import org.springframework.data.annotation.TypeAlias;

@Getter
//몽고디비에서 저장된 데이터를 자바의 class 역직렬화할때 명시해주는 역할
@TypeAlias("LikeNotification")
public class LikeNotification extends Notification {
    private final Long postId;
    //좋아요를 한 사용자들
    private final List<Long> likeIds;

    public LikeNotification(String id, Long userId,
            NotificationType type,
            Instant occurredAt, Instant createdAt, Instant lastUpdateAt,
            Instant deletedAt, Long postId, List<Long> likeIds) {
        super(id, userId, type, occurredAt, createdAt, lastUpdateAt, deletedAt);
        this.postId = postId;
        this.likeIds = likeIds;
    }

    public void addLiker(Long likeId, Instant occurredAt, Instant now, Instant retention) {
        this.likeIds.add(likeId);
        this.setOccurredAt(occurredAt);
        this.setLastUpdateAt(now);
        this.setDeletedAt(retention);
    }
}
