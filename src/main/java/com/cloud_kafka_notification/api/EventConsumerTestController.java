package com.cloud_kafka_notification.api;

import com.cloud_kafka_notification.comment.event.CommentEvent;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Evnet Consumer 호출 테스트 API")
public class EventConsumerTestController {
    private final Consumer<CommentEvent> comment;


    @PostMapping("/test/comment")
    @Operation(summary = "comment 저장")
    public void comment(@RequestBody  CommentEvent event){
        comment.accept(event);
    }
}
