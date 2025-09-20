package com.cloud_kafka_notification.api.controller;

import com.cloud_kafka_notification.comment.event.CommentEvent;
import com.cloud_kafka_notification.follow.event.FollowEvent;
import com.cloud_kafka_notification.like.event.LikeEvent;
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
    private final Consumer<LikeEvent> like;
    private final Consumer<FollowEvent> follow;


    @PostMapping("/test/comment")
    @Operation(summary = "comment 저장 및 삭제")
    public void comment(@RequestBody  CommentEvent event){
        comment.accept(event);
    }

    @PostMapping("/test/like")
    @Operation(summary = "like 저장 및 삭제")
    public void comment(@RequestBody LikeEvent event){
        like.accept(event);
    }

    @PostMapping("/test/follow")
    @Operation(summary = "follow 저장 및 삭제")
    public void comment(@RequestBody FollowEvent event){
        follow.accept(event);
    }
}
