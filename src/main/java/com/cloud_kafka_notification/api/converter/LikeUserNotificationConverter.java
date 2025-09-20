package com.cloud_kafka_notification.api.converter;

import com.cloud_kafka_notification.api.dto.converted.ConvertedLikeNotification;
import com.cloud_kafka_notification.like.event.LikeNotification;
import com.cloud_kafka_notification.postClient.dto.Post;
import com.cloud_kafka_notification.postClient.dto.PostClient;
import com.cloud_kafka_notification.postClient.dto.User;
import com.cloud_kafka_notification.postClient.dto.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LikeUserNotificationConverter {
    private final UserClient userClient;
    private final PostClient postClient;

    public ConvertedLikeNotification convert(LikeNotification notification) {
        //제일 마지막에 있는 사람이 좋아요를 마지막으로 누른 사람
        User user = userClient.getUser(notification.getLikeIds().getLast());
        Post post = postClient.getPost(notification.getPostId());

        return ConvertedLikeNotification.builder()
                .id(notification.getId())
                .type(notification.getType())
                .occurredAt(notification.getOccurredAt())
                .lastUpdatedAt(notification.getLastUpdateAt())
                .userName(user.getName())
                .userProfileImageUrl(user.getProfileImageUrl())
                .userCount(notification.getLikeIds().size())
                .postImageUrl(post.getImgUrl())
                .build();
    }
}
