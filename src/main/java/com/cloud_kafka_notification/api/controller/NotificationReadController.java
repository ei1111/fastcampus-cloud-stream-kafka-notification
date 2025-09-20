package com.cloud_kafka_notification.api.controller;

import com.cloud_kafka_notification.api.dto.response.SetLastReadAtResponse;
import com.cloud_kafka_notification.redis.service.LastReadAtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "사용자 알림 센터 API")
@RequestMapping("/v1/user-notifications")
public class NotificationReadController {
    private final LastReadAtService service;

    @PutMapping("/{userId}/read")
    @Operation(summary = "사용자가 알림 목록을 읽은 시간 기록")
    public SetLastReadAtResponse setLastReadAt(@PathVariable long userId) {
        Instant lastReadAt = service.setLastReadAt(userId);
        return new SetLastReadAtResponse(lastReadAt);
    }
}
