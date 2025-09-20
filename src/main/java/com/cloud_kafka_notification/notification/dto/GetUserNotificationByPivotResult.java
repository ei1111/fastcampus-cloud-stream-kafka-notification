package com.cloud_kafka_notification.notification.dto;

import com.cloud_kafka_notification.notification.event.Notification;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Slice;

@Getter
@AllArgsConstructor(access =  AccessLevel.PRIVATE)
public class GetUserNotificationByPivotResult {
    private  List<Notification> notifications;
    private  boolean hasNext;

    public static GetUserNotificationByPivotResult from(Slice<Notification> slice) {
        return new GetUserNotificationByPivotResult(slice.toList(), slice.hasNext());
    }
}
