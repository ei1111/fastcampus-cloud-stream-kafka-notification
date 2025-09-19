package com.cloud_kafka_notification.follow.task;

import com.cloud_kafka_notification.follow.event.FollowEvent;
import com.cloud_kafka_notification.follow.event.FollowNotification;
import com.cloud_kafka_notification.notification.service.NotificationSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FollowAddTask {
    private final NotificationSaveService saveService;

    public void processEvent(FollowEvent event) {
        saveService.save(FollowNotification.from(event));
    }
}
