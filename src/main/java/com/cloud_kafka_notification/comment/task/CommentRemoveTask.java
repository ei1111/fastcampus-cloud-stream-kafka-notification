package com.cloud_kafka_notification.comment.task;

import com.cloud_kafka_notification.comment.event.CommentEvent;
import com.cloud_kafka_notification.commentClient.dto.CommentClient;
import com.cloud_kafka_notification.notification.event.NotificationType;
import com.cloud_kafka_notification.notification.service.NotificationGetService;
import com.cloud_kafka_notification.notification.service.NotificationRemoveService;
import com.cloud_kafka_notification.postClient.dto.Post;
import com.cloud_kafka_notification.postClient.dto.PostClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommentRemoveTask {

    private final PostClient postClient;
    private final NotificationGetService getService;
    private final NotificationRemoveService removeService;

    public void processEvent(CommentEvent event) {
        //게시물을 작성한 사람이 댓글을 단 사람이라면 아무런 작동하지 않음
        Post post = postClient.getPost(event.getPostId());

        if (event.isSameUser(post)) {
            return;
        }
        //알림을 지우는 로직
        getService.getNotificationByTypeAndCommentId(NotificationType.COMMENT, event.getCommentId())
                //값이 있을경우 삭제, 값이 없으면 90일에 삭제되니 놔두기
                .ifPresent(notification -> removeService.deleteById(notification.getId()));
    }
}
