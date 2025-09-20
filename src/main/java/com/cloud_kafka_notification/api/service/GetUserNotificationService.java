package com.cloud_kafka_notification.api.service;

import com.cloud_kafka_notification.api.converter.CommentUserNotificationConverter;
import com.cloud_kafka_notification.api.converter.FollowUserNotificationConverter;
import com.cloud_kafka_notification.api.converter.LikeUserNotificationConverter;
import com.cloud_kafka_notification.api.dto.converted.ConvertedNotification;
import com.cloud_kafka_notification.api.dto.response.GetUserNotificationResult;
import com.cloud_kafka_notification.comment.event.CommentNotification;
import com.cloud_kafka_notification.follow.event.FollowNotification;
import com.cloud_kafka_notification.like.event.LikeNotification;
import com.cloud_kafka_notification.notification.dto.GetUserNotificationByPivotResult;
import com.cloud_kafka_notification.notification.service.NotificationListService;
import java.time.Instant;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserNotificationService {

    private final NotificationListService listService;
    private final CommentUserNotificationConverter commentConverter;
    private final LikeUserNotificationConverter likeConverter;
    private final FollowUserNotificationConverter followConverter;

    public GetUserNotificationResult getUserNotificationsByPivot(long userId, Instant pivot) {
        //알림 목록 조회
        GetUserNotificationByPivotResult result = listService.getUserNotificationsByPivot(userId,
                pivot);

        //알림 목록을 순회하면서 디비 알림 -> 사용자 알림으로 변환
        List<ConvertedNotification> convertedNotifications = result.getNotifications().stream()
                .map(notification -> switch (notification.getType()) {
                    case COMMENT -> commentConverter.convert((CommentNotification) notification);
                    case LIKE -> likeConverter.convert((LikeNotification) notification);
                    case FOLLOW -> followConverter.convert((FollowNotification) notification);
                    default -> throw new IllegalStateException(
                            "Unexpected value: " + notification.getType());
                }).toList();

        return new GetUserNotificationResult(convertedNotifications, result.isHasNext());
    }
}
