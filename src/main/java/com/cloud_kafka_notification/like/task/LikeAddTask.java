package com.cloud_kafka_notification.like.task;

import com.cloud_kafka_notification.global.util.InstantDateTime;
import com.cloud_kafka_notification.global.util.NotificationIdGenerator;
import com.cloud_kafka_notification.like.event.LikeEvent;
import com.cloud_kafka_notification.like.event.LikeNotification;
import com.cloud_kafka_notification.notification.event.Notification;
import com.cloud_kafka_notification.notification.event.NotificationType;
import com.cloud_kafka_notification.notification.service.NotificationGetService;
import com.cloud_kafka_notification.notification.service.NotificationSaveService;
import com.cloud_kafka_notification.postClient.dto.Post;
import com.cloud_kafka_notification.postClient.dto.PostClient;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class LikeAddTask {

    private final PostClient postClient;
    private final NotificationGetService getService;
    private final NotificationSaveService notificationSaveService;

    public void processEvent(LikeEvent event) {
        //본인 것에 좋아요를 누르면 알림이 뜨지 않는다
        Post post = postClient.getPost(event.getPostId());

        if (post == null) {
            log.error("Post with id {} not found", event.getPostId());
            return;
        }
        //본인 게시물 일때 알람 처리하지 않음
        if (post.isSameUser(event.getUserId())) {
            return;
        }

        //기존에 좋아요가 있는데 마지막에 누르면 마지막거가 최신
        //2가지 상황: 1. 처음에 좋아요가 있었던 상황, 2. 처음 좋아요를 누른 상황
        //likeNotification 1. 신규 생성, 2. 업데이트
        //저장
        notificationSaveService.save(createOrUpdateNotification(post, event));
    }

    private Notification createOrUpdateNotification(Post post, LikeEvent event) {
        return getService.getNotificationByTypeAndPostId(NotificationType.LIKE, event.getPostId())
                //업데이트(좋아해준 사람, 마지막 업데이트 시간을 현재시간으로 변경, 알림이 삭제될 시간 업데이트)
                .map(n -> updateNotification((LikeNotification) n, event))
                //알림 신규생성
                .orElseGet(() -> createNotification(post, event));
    }

    private Notification updateNotification(LikeNotification likeNotification, LikeEvent event) {
         likeNotification.addLiker(event.getUserId(), event.getCreatedAt(), InstantDateTime.now(), InstantDateTime.deleteDateTime());
         return likeNotification;
    }

    private Notification createNotification(Post post, LikeEvent event) {
        return new LikeNotification(
                NotificationIdGenerator.generate(),
                post.getUserId(),
                NotificationType.LIKE,
                event.getCreatedAt(),
                InstantDateTime.now(),
                InstantDateTime.now(),
                InstantDateTime.deleteDateTime(),
                post.getId(),
                List.of(event.getUserId())
        );
    }
}
