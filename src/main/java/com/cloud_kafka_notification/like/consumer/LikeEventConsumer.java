package com.cloud_kafka_notification.like.consumer;

import com.cloud_kafka_notification.like.event.LikeEvent;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

//이 부분이 포인트
@Slf4j
@Component
public class LikeEventConsumer {

    //application-event.yml에 정의된 함수이름(like)
    @Bean("like")
    public Consumer<LikeEvent> like() {
        return event -> log.info("Received comment event {}", event.toString());
    }
}
