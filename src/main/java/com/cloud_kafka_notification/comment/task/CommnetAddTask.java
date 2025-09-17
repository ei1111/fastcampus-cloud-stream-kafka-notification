package com.cloud_kafka_notification.comment.task;

import com.cloud_kafka_notification.comment.event.CommentEvent;
import com.cloud_kafka_notification.comment.event.CommentNotification;
import com.cloud_kafka_notification.commentClient.dto.Comment;
import com.cloud_kafka_notification.commentClient.dto.CommentClient;
import com.cloud_kafka_notification.global.util.NotificationIdGenerator;
import com.cloud_kafka_notification.notification.event.Notification;
import com.cloud_kafka_notification.notification.event.NotificationType;
import com.cloud_kafka_notification.notification.service.NotificationService;
import com.cloud_kafka_notification.postClient.dto.Post;
import com.cloud_kafka_notification.postClient.dto.PostClient;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommnetAddTask {
    private final PostClient postClient;
    private final CommentClient commentClient;
    private final NotificationService notificationService;

    public void processEvent(CommentEvent event) {
        //내가 내 게시물에 댓글을 달면 알림은 무시.
        Post post = postClient.getPost(event.getPostId());

        if (event.isSameUser(post)) {
            return;
        }

        Comment comment = commentClient.getComment(event.getCommentId());

        //알림설정
        Notification notification = createNotification(post, comment);

        //저장
        notificationService.save(notification);
    }

    //댓글 알림을 생성하는 함수
    private Notification createNotification(Post post, Comment comment) {
        Instant now = Instant.now();

        return new CommentNotification(
                NotificationIdGenerator.generate(),
                post.getUserId(),
                NotificationType.COMMENT,
                comment.getCreatedAt(),
                now,
                now,
                now.plus(90, ChronoUnit.DAYS),
                post.getId(),
                comment.getUserId(),
                comment.getContent(),
                comment.getId()
        );
    }
}
