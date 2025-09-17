package com.cloud_kafka_notification.comment.consumer;

import com.cloud_kafka_notification.comment.event.CommentEvent;
import com.cloud_kafka_notification.comment.event.CommentEventType;
import com.cloud_kafka_notification.comment.task.CommentRemoveTask;
import com.cloud_kafka_notification.comment.task.CommnetAddTask;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

//이 부분이 포인트
@Slf4j
@Component
@RequiredArgsConstructor
public class CommentEventConsumer {
    private final CommnetAddTask commnetAddTask;
    private final CommentRemoveTask commentRemoveTask;

    //application-event.yml에 정의된 함수이름(comment)
    @Bean("comment")
    public Consumer<CommentEvent> comment() {
        return event -> {
            if (event.getType() == CommentEventType.ADD) {
                commnetAddTask.processEvent(event);
            } else if (event.getType() == CommentEventType.REMOVE) {
                commentRemoveTask.processEvent(event);
            }
        };
    }
}
