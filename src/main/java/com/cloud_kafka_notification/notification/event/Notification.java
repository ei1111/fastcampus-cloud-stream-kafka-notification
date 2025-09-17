package com.cloud_kafka_notification.notification.event;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
//어떤 컬렉션에 넣을것인지
@Document("notifications")
@AllArgsConstructor
public abstract class Notification {

    private String id;
    private Long userId;
    private NotificationType type;

    //알림 대상인 실제 이벤트가 발생한 시간(인스타에서 몇 분전)
    private Instant occurredAt;

    //데이터가 생성된 시간
    private Instant createdAt;

    //데이터가 수정된 사긴
    private Instant lastUpdateAt;

    //알림이 삭제될 시간
    private Instant deletedAt;
    
    
}
