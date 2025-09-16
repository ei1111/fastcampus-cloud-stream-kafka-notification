package com.cloud_kafka_notification.follow.event;

import lombok.RequiredArgsConstructor;

//프로듀스 되는 부분에서 정의
@RequiredArgsConstructor
public enum FollowEventType {
    ADD("추가"),
    DELETE("삭제");


    private final String desc;
}
