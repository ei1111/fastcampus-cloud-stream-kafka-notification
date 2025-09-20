package com.cloud_kafka_notification.client.dto;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Comment {
    private Long id;
    //누구의 댓글인지
    private Long userId;
    //댓글내용
    private String content;

    //댓글 생성 시간
    private Instant createdAt;
}
