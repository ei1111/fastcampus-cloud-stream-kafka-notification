package com.cloud_kafka_notification.like.consumer;

import com.cloud_kafka_notification.like.event.LikeEvent;
import com.cloud_kafka_notification.like.task.LikeAddTask;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

//이 부분이 포인트
@Slf4j
@Component
@RequiredArgsConstructor
public class LikeEventConsumer {
    private final LikeAddTask likeAddTask;

    //application-event.yml에 정의된 함수이름(like)
    @Bean("like")
    public Consumer<LikeEvent> like() {
        return event -> {
            switch (event.getType()) {
                case ADD -> likeAddTask.processEvent(event);
                //case REMOVE -> commentRemoveTask.processEvent(event);
                default -> throw new IllegalArgumentException("Unsupported type: " + event.getType());
            }
        };

    }
}
