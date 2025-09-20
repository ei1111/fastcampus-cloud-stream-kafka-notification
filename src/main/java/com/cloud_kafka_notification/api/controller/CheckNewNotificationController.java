package com.cloud_kafka_notification.api.controller;

import com.cloud_kafka_notification.api.dto.response.CheckNewNotificationResponse;
import com.cloud_kafka_notification.api.service.CheckNewNotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "사용자 알림센터 API")
@RequiredArgsConstructor
@RequestMapping("/v1/user-notifications")
public class CheckNewNotificationController {

    private final CheckNewNotificationService service;


    @GetMapping("/{userId}/new")
    @Operation(summary = "사용자 신규 알림 여부 조회")
    public CheckNewNotificationResponse checkNew(@PathVariable Long userId) {
        return new CheckNewNotificationResponse(service.checkNewNotification(userId));
    }
}
