package com.cloud_kafka_notification.follow.consumer;

import com.cloud_kafka_notification.follow.event.FollowEvent;
import com.cloud_kafka_notification.follow.task.FollowAddTask;
import com.cloud_kafka_notification.follow.task.FollowRemoveTask;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

//이 부분이 포인트
@Slf4j
@Component
@RequiredArgsConstructor
public class FollowEventConsumer {
    private final FollowAddTask followAddTask;
    private final FollowRemoveTask followRemoveTask;
    //application-event.yml에 정의된 함수이름(follow)
    @Bean("follow")
    public Consumer<FollowEvent> follow() {
        return event -> {
            switch (event.getType()) {
                case ADD -> followAddTask.processEvent(event);
                case REMOVE -> followRemoveTask.processEvent(event);
                default -> throw new IllegalArgumentException("Unsupported type: " + event.getType());
            }
        };
    }

}
