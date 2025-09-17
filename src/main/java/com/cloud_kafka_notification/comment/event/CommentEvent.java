package com.cloud_kafka_notification.comment.event;

import com.cloud_kafka_notification.postClient.dto.Post;
import java.util.Objects;
import lombok.Data;

//댓글 관련 처리
//메시지 규격은 프로듀서 하는 쪽에서 정함ㄴ
@Data
public class CommentEvent {
    //이벤트 종류 구별(생성,추가)
    private CommentEventType type;
    //어떤 게시글에 댓글 달렸는지
    private Long postId;
    //누가 댓글 달았는지
    private Long userId;
    //댓글은 뭔지(댓글 내용이 변경될수 있어서 관련 내용은 만들지 않음)
    private Long commentId;


    public boolean isSameUser(Post post){
        return Objects.equals(userId, post.getUserId());
    }
}
