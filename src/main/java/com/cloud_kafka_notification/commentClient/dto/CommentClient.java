package com.cloud_kafka_notification.commentClient.dto;

import com.cloud_kafka_notification.postClient.dto.Post;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class CommentClient {
    private final Map<Long, Comment> comments = new HashMap<>();
    private final Instant now = Instant.now();

    public CommentClient() {
        comments.put(1L, new Comment(1L,1L,"imag1", now));
        comments.put(2L, new Comment(2L,2L, "imag2", now));
        comments.put(3L, new Comment(3L, 3L,"imag3", now));
    }

    public Comment getComment(Long postId) {
        return comments.get(postId);
    }
}
