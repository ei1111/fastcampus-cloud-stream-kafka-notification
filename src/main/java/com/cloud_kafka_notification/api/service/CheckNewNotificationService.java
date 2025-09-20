package com.cloud_kafka_notification.api.service;

import com.cloud_kafka_notification.notification.service.NotificationGetService;
import com.cloud_kafka_notification.redis.service.LastReadAtService;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckNewNotificationService {

    private final NotificationGetService getService;
    private final LastReadAtService  lastReadAtService;

    public boolean checkNewNotification(long userId) {
        // lastReadAt과  latesUpdateAt(알림들중에 가장 최신 업데이트된 값)을 비교
        // lastReadAt인 읽은 시간이 latesUpdateAt보다 빠르면 읽을 알람이 없다.
        Instant latestUpdatedAt = getService.getLatestUpdatedAt(userId);

        if (latestUpdatedAt == null) {
            return false;
        }

        Instant lastReadAt = lastReadAtService.getLastReadAt(userId);

        if (lastReadAt == null) {
            return true;
        }

        //업데트 된 시간이 최근 일 경우
        return latestUpdatedAt.isAfter(lastReadAt);
    }
}
