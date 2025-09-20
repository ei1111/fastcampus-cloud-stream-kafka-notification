package com.cloud_kafka_notification.api.converter;

import com.cloud_kafka_notification.api.dto.converted.ConvertedCommentNotification;
import com.cloud_kafka_notification.comment.event.CommentNotification;
import com.cloud_kafka_notification.postClient.dto.Post;
import com.cloud_kafka_notification.postClient.dto.PostClient;
import com.cloud_kafka_notification.postClient.dto.User;
import com.cloud_kafka_notification.postClient.dto.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentUserNotificationConverter {
    private final UserClient userClient;
    private final PostClient postClient;

    public ConvertedCommentNotification convert(CommentNotification notification) {
        User user = userClient.getUser(notification.getWriterId());
        Post post = postClient.getPost(notification.getPostId());

        return ConvertedCommentNotification.builder()
                .id(notification.getId())
                .type(notification.getType())
                .occurredAt(notification.getOccurredAt())
                .lastUpdatedAt(notification.getLastUpdateAt())
                .userName(user.getName())
                .userProfileImageUrl(user.getProfileImageUrl())
                .comment(notification.getComment())
                .postImageUrl(post.getImgUrl())
                .build();
    }
}
