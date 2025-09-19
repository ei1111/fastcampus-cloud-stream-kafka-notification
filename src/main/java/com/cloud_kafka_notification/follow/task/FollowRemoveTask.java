package com.cloud_kafka_notification.follow.task;

import com.cloud_kafka_notification.follow.event.FollowEvent;
import com.cloud_kafka_notification.follow.event.FollowNotification;
import com.cloud_kafka_notification.notification.event.NotificationType;
import com.cloud_kafka_notification.notification.service.NotificationGetService;
import com.cloud_kafka_notification.notification.service.NotificationRemoveService;
import com.cloud_kafka_notification.notification.service.NotificationSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FollowRemoveTask {
    private final NotificationGetService getService;
    private final NotificationRemoveService removeService;

    public void processEvent(FollowEvent event) {
        //알림이 없어면 삭제 안해도 되는게 90일이 지나면 알아서 알림을 삭제 시켜주는 기능이 있기 때문이다.
        getService.getNotificationByTypeAndUserIdAndFollowerId(NotificationType.FOLLOW, event.getTargetUserId(), event.getUserId())
                .ifPresent(notification -> removeService.deleteById(notification.getId()));
    }
}
