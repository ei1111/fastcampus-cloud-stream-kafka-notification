package com.cloud_kafka_notification.api.dto.response;

import com.cloud_kafka_notification.api.service.GetUserNotificationService;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import java.time.Instant;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserNotificationListResponse {
    //유저 알림
    private List<UserNotificationResponse> notifications;
    //다음 번에 알림이 있는지
    private boolean hasNext;
    //마지막 알림의 발생한 날짜
    private Instant pivot;

    public static UserNotificationListResponse of(GetUserNotificationResult result){

        //ConvertedNotification -> UserNotificationResponse
        List<UserNotificationResponse> notifications = result.getConvertedNotifications().stream()
                .map(UserNotificationResponse::of)
                .toList();

        //pivot 생성
        Instant pivot = (result.isHasNext() && !notifications.isEmpty()) ? notifications.getLast()
                .getOccurredAt() : null;

        return new UserNotificationListResponse(
                notifications,
                result.isHasNext(),
                pivot
        );
    }
}
