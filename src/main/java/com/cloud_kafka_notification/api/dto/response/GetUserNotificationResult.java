package com.cloud_kafka_notification.api.dto.response;

import com.cloud_kafka_notification.api.dto.converted.ConvertedNotification;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetUserNotificationResult {
    private List<ConvertedNotification> convertedNotifications;
    private boolean hasNext;
}
