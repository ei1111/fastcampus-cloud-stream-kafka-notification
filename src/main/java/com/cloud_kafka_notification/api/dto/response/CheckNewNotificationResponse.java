package com.cloud_kafka_notification.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CheckNewNotificationResponse {

    private Boolean hasNew;
}
