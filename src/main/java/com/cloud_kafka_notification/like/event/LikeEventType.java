package com.cloud_kafka_notification.like.event;

import lombok.RequiredArgsConstructor;

//프로듀스 되는 부분에서 정의
@RequiredArgsConstructor
public enum LikeEventType {
    ADD("추가"),
    REMOVE("삭제");


    private final String desc;
}
