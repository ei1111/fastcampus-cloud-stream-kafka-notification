package com.cloud_kafka_notification.like.task;

import com.cloud_kafka_notification.global.util.InstantDateTime;
import com.cloud_kafka_notification.like.event.LikeEvent;
import com.cloud_kafka_notification.like.event.LikeNotification;
import com.cloud_kafka_notification.notification.event.NotificationType;
import com.cloud_kafka_notification.notification.service.NotificationGetService;
import com.cloud_kafka_notification.notification.service.NotificationRemoveService;
import com.cloud_kafka_notification.notification.service.NotificationSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LikeRemoveTask {

    private final NotificationGetService getService;
    private final NotificationRemoveService removeService;
    private final NotificationSaveService saveService;

    public void processEvent(LikeEvent event) {
        LikeNotification likeNotification = getService.getNotificationByTypeAndPostId(NotificationType.LIKE, event.getPostId())
                .map(LikeNotification.class::cast)
                .orElseThrow(() -> new IllegalArgumentException("No like found for postId: " + event.getPostId()));

        //likers에서 event,userId 1.제거 후 빈값이면 알림 삭제, 2.제거 후 데이터 남아 있으면 업데이트
        removeLikerAndUpdateNotification(event, likeNotification);
    }

    private void removeLikerAndUpdateNotification(LikeEvent event, LikeNotification likeNotification) {
        likeNotification.removeLiker(event.getUserId(), InstantDateTime.now());

        if (likeNotification.getLikeIds().isEmpty()) {
            removeService.deleteById(likeNotification.getId());
        }else{
            saveService.save(likeNotification);
        }
    }
}
