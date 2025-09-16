package com.cloud_kafka_notification.follow.event;

import java.time.Instant;
import lombok.Data;

//댓글 관련 처리
//메시지 규격은 프로듀서 하는 쪽에서 정함
@Data
public class FollowEvent {

    //이벤트 종류 구별(생성,추가)
    private FollowEventType type;
    //누가 댓글 달았는지
    private Long userId;

    //누구를 팔로우 했는지
    private Long targetUserId;
    //팔로우한 시간
    private Instant createdAt;
}
