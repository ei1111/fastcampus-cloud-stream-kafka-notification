package com.cloud_kafka_notification.api.dto.response;

import com.cloud_kafka_notification.api.dto.converted.ConvertedCommentNotification;
import com.cloud_kafka_notification.notification.event.NotificationType;
import java.time.Instant;
import lombok.Getter;

@Getter
public class CommentUserNotificationResponse extends UserNotificationResponse {

    private final String userName;
    private final String userProfileImage;
    private final String comment;
    private final String postImageUrl;

    public CommentUserNotificationResponse(String id,
            NotificationType type,
            Instant occurredAt, String userName, String userProfileImage, String comment,
            String postImageUrl) {
        super(id, type, occurredAt);
        this.userName = userName;
        this.userProfileImage = userProfileImage;
        this.comment = comment;
        this.postImageUrl = postImageUrl;
    }

    public static CommentUserNotificationResponse of(ConvertedCommentNotification notification) {
        return new CommentUserNotificationResponse(
                notification.getId(),
                notification.getType(),
                notification.getOccurredAt(),
                notification.getUserName(),
                notification.getUserProfileImageUrl(),
                notification.getComment(),
                notification.getPostImageUrl()
        );
    }
}
