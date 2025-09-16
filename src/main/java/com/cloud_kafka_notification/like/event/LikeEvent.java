package com.cloud_kafka_notification.like.event;

import java.time.Instant;
import lombok.Data;

//댓글 관련 처리
//메시지 규격은 프로듀서 하는 쪽에서 정함ㄴ
@Data
public class LikeEvent {
    //이벤트 종류 구별(생성,추가)
    private LikeEventType type;
    //어떤 게시글에 댓글 달렸는지
    private Long postId;
    //누가 댓글 달았는지
    private Long userId;
    //언제 좋아요를 눌렀는지?
    private Instant createdAt;
}
