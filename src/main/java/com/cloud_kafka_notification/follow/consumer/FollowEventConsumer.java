package com.cloud_kafka_notification.follow.consumer;

import com.cloud_kafka_notification.follow.event.FollowEvent;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

//이 부분이 포인트
@Slf4j
@Component
public class FollowEventConsumer {

    //application-event.yml에 정의된 함수이름(follow)
    @Bean("follow")
    public Consumer<FollowEvent> follow() {
        return event -> log.info("Received comment event {}", event.toString());
    }
}
