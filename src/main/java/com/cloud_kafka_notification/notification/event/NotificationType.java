package com.cloud_kafka_notification.notification.event;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum NotificationType {
    LIKE("좋아요알림"),
    COMMENT("댓글알림"),
    FOLLOW("팔로우알림");

    private final String desc;
}
