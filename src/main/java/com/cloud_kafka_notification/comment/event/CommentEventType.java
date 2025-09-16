package com.cloud_kafka_notification.comment.event;

import lombok.RequiredArgsConstructor;

//프로듀스 되는 부분에서 정의
@RequiredArgsConstructor
public enum CommentEventType {
    ADD("추가"),
    DELETE("삭제");


    private final String desc;
}
