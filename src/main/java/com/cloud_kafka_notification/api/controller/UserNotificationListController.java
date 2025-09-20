package com.cloud_kafka_notification.api.controller;

import com.cloud_kafka_notification.api.dto.response.UserNotificationListResponse;
import com.cloud_kafka_notification.api.service.GetUserNotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "사용자 알림 센터 API")
@RequestMapping("/v1/user-notification")
public class UserNotificationListController {
    private final GetUserNotificationService getUserNotificationService;


    @GetMapping("/{userId}")
    @Operation(summary = "사용자 알림 목록 조회")
    public UserNotificationListResponse getNotifications(
            @PathVariable(value = "userId") Long userId,
            @RequestParam(value = "pivot", required = false) @DateTimeFormat(iso = ISO.DATE)Instant pivot) {
        return UserNotificationListResponse.of(getUserNotificationService.getUserNotificationsByPivot(userId, pivot));
    }
}
