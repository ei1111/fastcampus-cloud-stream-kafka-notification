package com.cloud_kafka_notification.api.converter;

import com.cloud_kafka_notification.api.dto.converted.ConvertedFollowNotification;
import com.cloud_kafka_notification.follow.event.FollowNotification;
import com.cloud_kafka_notification.postClient.dto.PostClient;
import com.cloud_kafka_notification.postClient.dto.User;
import com.cloud_kafka_notification.postClient.dto.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FollowUserNotificationConverter {
    private final UserClient userClient;
    private final PostClient postClient;

    public ConvertedFollowNotification convert(FollowNotification notification) {
        User user = userClient.getUser(notification.getFollowerId());
        Boolean isFollowing = userClient.getIsFollowing(notification.getUserId(),
                notification.getFollowerId());

        return ConvertedFollowNotification.builder()
                .id(notification.getId())
                .type(notification.getType())
                .occurredAt(notification.getOccurredAt())
                .lastUpdatedAt(notification.getLastUpdateAt())
                .userName(user.getName())
                .userProfileImageUrl(user.getProfileImageUrl())
                .isFollowing(isFollowing)
                .build();
    }
}
