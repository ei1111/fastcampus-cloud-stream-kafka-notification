package com.cloud_kafka_notification.comment.event;

import com.cloud_kafka_notification.notification.event.Notification;
import com.cloud_kafka_notification.notification.event.NotificationType;
import java.time.Instant;
import lombok.Getter;
import org.springframework.data.annotation.TypeAlias;

@Getter
//몽고디비에서 저장된 데이터를 자바의 class 역직렬화할때 명시해주는 역할
@TypeAlias("CommentNotification")
public class CommentNotification extends Notification {
    private final Long postId;
    //댓글 작성자 아이디
    private final Long writerId;
    private final String comment;
    private final Long commentId;


    public CommentNotification(
            String id
            , Long userId,
            NotificationType type,
            Instant occurredAt, Instant createdAt, Instant lastUpdateAt,
            Instant deletedAt, Long postId, Long writerId, String comment, Long commentId) {
        super(id, userId, type, occurredAt, createdAt, lastUpdateAt, deletedAt);
        this.postId = postId;
        this.writerId = writerId;
        this.comment = comment;
        this.commentId = commentId;
    }
}
